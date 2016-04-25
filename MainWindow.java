import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JPanel
{
    private static JFrame f = new JFrame("GetRolling");
    private static JButton addGroupButton = new JButton("Add Group");
    private static ArrayList<Group> groupList = new ArrayList<Group>();
    
    private final static int MAXGROUPS = 5;
    
    public MainWindow()
    {
        this.add(addGroupButton);
        addGroupButton.addActionListener(new addGroupListener());
    }
    
    private static class addGroupListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            if (groupList.size() < MAXGROUPS - 1)
            {
                addGroup();
            }
            else if (groupList.size() == MAXGROUPS - 1)
            {
                addGroup();
                addGroupButton.setEnabled(false);
            }
        }
    }
    
    public static void refresh()
    {
        f.pack();
    }
    
    private static void addGroup()
    {
        Group temp = new Group();
        f.add(temp);
        groupList.add(temp);
        f.pack();
    }
    
    public static void deleteGroup(Group current)
    {
        int index = groupList.indexOf(current);
        if (index != -1)
        {
            f.remove(groupList.get(index));
            groupList.remove(index);
            
            if (groupList.size() == MAXGROUPS - 1)
            {
                addGroupButton.setEnabled(true);
            }
            
            f.pack();
        }
    }
    
    public static void main(String[] args)
    {
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(640,480);
        f.add(new MainWindow());
        addGroup();
        f.setResizable(false);
        f.pack();
        f.setVisible(true);        
    }
}