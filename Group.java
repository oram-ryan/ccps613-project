import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Box;

/**
 * Write a description of class Group here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Group extends JPanel
{
    private ArrayList<Die> diceList = new ArrayList<Die>();
    private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    private Integer[] options = new Integer[]{2,4,6,8,10,12,20,100};
    private JPanel panel = new JPanel();
    private boolean deleteMode = false;
    
    public Group()
    {
        this.setPreferredSize(new Dimension(300,100));
        this.setBorder(BorderFactory.createEtchedBorder());
         
        JButton rollButton = new JButton("Roll");
        JButton editButton = new JButton("Edit");
       
        this.add(rollButton);
        this.add(editButton);
        this.add(panel);
        
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(295,200));
        
        
        rollButton.addActionListener(new roll());
        editButton.addActionListener(new editDice());
      
    }
    
    public class addDie implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            Integer dieType = (Integer) JOptionPane.showInputDialog(null, "What sided die?", "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            diceList.add(new Die(dieType));
            JLabel temp = new JLabel("d"+dieType.toString());
            panel.add(temp);
            temp.addMouseListener(new deleteMouse());
            labelList.add(temp);
            panel.revalidate();
            panel.repaint();
        }
    }
    
    public class roll implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            JFrame rollFrame = new JFrame("Roll Results");
            rollFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rollFrame.setLayout(new GridLayout(1,2));
            Box rollResults1 = Box.createVerticalBox();
            Box rollResults2 = Box.createVerticalBox();
            rollFrame.add(rollResults1);
            rollFrame.add(rollResults2);
            for (Die d : diceList)
            {
                rollResults1.add(new JLabel("d"+Integer.toString(d.getSides())));
            }
            for (Die d : diceList)
            {
                rollResults2.add(new JLabel(Integer.toString(d.roll())));
            }
            rollResults1.revalidate();
            rollResults2.repaint();
            rollFrame.pack();
            rollFrame.setVisible(true);   
        }
    }
    
    public class editDice implements ActionListener 
    {
        JButton addButton = new JButton("+");
        public void actionPerformed(ActionEvent ae)
        { 
            deleteMode = deleteMode ? false : true;
   
            if (deleteMode){                
                panel.add(addButton);
                addButton.addActionListener(new addDie());
                panel.setBackground(Color.red);
                panel.repaint();
                panel.revalidate();            
            } else if (!deleteMode){
                panel.remove(addButton);
                for (ActionListener al : addButton.getActionListeners()){
                    addButton.removeActionListener(al);
                }
                panel.setBackground(null);
                panel.repaint();
                panel.revalidate();                
            }
            
        }
    }
    
    private class deleteMouse implements MouseListener
    {
        public void mouseClicked(MouseEvent e)
        {
            if(deleteMode)
            {
                Object source = e.getSource();
                if (source instanceof JLabel)
                {
                    int index = labelList.indexOf(source);
                    diceList.remove(index);
                    labelList.remove(index);
                    panel.remove(index);
                    panel.revalidate();
                    panel.repaint();
                }
            }
        }
        
        public void mouseExited(MouseEvent e)
        {        
        }
        
        public void mouseEntered(MouseEvent e)
        {
        }
        
        public void mousePressed(MouseEvent e)
        {
        }
        
        public void mouseReleased(MouseEvent e)
        {
        }
    }
}
