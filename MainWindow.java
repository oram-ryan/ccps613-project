import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JPanel
{
    private static JFrame f = new JFrame("GetRolling");
    private static JButton addGroupButton = new JButton("Add Group");
    private static ArrayList<Group> groupList = new ArrayList<Group>();
    private static ArrayList<JPanel> deletePanelList = new ArrayList<JPanel>();
    private static ArrayList<JButton> deleteList = new ArrayList<JButton>();
    private static int count = 0;
    private final static int MAXGROUPS = 6;
    
    public MainWindow()
    {
        addGroupButton.addActionListener(new deleteGroupListener());
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
        count++;
        JPanel delPanTemp = new JPanel();
        deletePanelList.add(delPanTemp); 
        JButton delTemp = new JButton("Delete Group " + count);
        delTemp.addActionListener(new deleteGroupListener());
        deleteList.add(delTemp);
        delPanTemp.add(delTemp);
        f.add(delPanTemp);
        f.pack();
    }
    
    private static class deleteGroupListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            Object source = ae.getSource();
            
            if (groupList.size() == MAXGROUPS - 1)
            {
                addGroupButton.setEnabled(true);
            }
            
            if (source instanceof JButton)
            {
                int index = deleteList.indexOf(source);
                if (index != -1)
                {
                    f.remove(groupList.get(index));
                    f.remove(deletePanelList.get(index));
                    f.remove((JButton) source);
                    groupList.remove(index);
                    deleteList.remove(index);
                    deletePanelList.remove(index);
                    f.pack();
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(640,480);
        f.add(new MainWindow());
        addGroup();
        f.pack();
        f.setVisible(true);        
    }
}