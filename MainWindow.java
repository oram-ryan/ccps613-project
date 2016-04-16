import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JPanel
{
    public static JFrame f = new JFrame("GetRolling");
    
    public MainWindow()
    {
        this.setSize(new Dimension(300,50));
        this.setBorder(BorderFactory.createEtchedBorder());
        
        JButton addGroupButton = new JButton("Add Group");
        this.add(addGroupButton);
        
        addGroupButton.addActionListener(new ButtonListener());
    }
    
    private class ButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            f.add(new Group());
            f.pack();
            f.setVisible(true);
        }
    }
    
    public static void main(String[] args)
    {
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.add(new MainWindow());
        f.add(new Group());
        f.pack();
        f.setVisible(true);        
    }
}