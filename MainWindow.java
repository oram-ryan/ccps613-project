import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JPanel
{
    private static JFrame f = new JFrame("GetRolling");
    private static JButton addGroupButton = new JButton("Add Group");
    private static int groupCount = 0;
    
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
            f.add(new Group());
            
            // Disables "Add Group" if max has been reached
            if (++groupCount == MAXGROUPS)
            {
                addGroupButton.setEnabled(false);
            }
            
            f.pack();
        }
    }
    
    public static void refresh()
    {
        f.pack();
    }
    
    public static void deleteGroup(Group current)
    {
        f.remove(current);
        
        // Reenables "Add Group" if max had been reached before removal
        if (groupCount-- == MAXGROUPS)
        {
            addGroupButton.setEnabled(true);
        }
        
        f.pack();
    }
    
    public static void main(String[] args)
    {
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(640,480);
        
        f.add(new MainWindow());
        f.add(new Group());
        groupCount++;
        
        f.setResizable(false);
        f.pack();
        f.setVisible(true);        
    }
}