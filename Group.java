import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class Group here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Group extends JPanel
{
    private Group current = this;
    private boolean deleteMode = false;
    
    private ArrayList<Die> diceList = new ArrayList<Die>();
    private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    private ArrayList<JLabel> labelXList = new ArrayList<JLabel>();
    private Integer[] options = new Integer[]{2,4,6,8,10,12,20,100};
    
    private JPanel buttonPanel = new JPanel();
    private JPanel addDiePanel = new JPanel();
    private JPanel diePanel = new JPanel();
    
    private JButton rollButton = new JButton("Roll");
    private JButton editButton = new JButton("Edit");
    private JButton addButton = new JButton("+");
    
    public Group()
    {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        buttonPanel.add(rollButton);
        buttonPanel.add(editButton);
        addDiePanel.add(addButton);
        
        this.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
        
        this.add(diePanel);
        diePanel.setLayout(new FlowLayout());
        diePanel.setPreferredSize(new Dimension(295,25));
        
        this.add(addDiePanel);
        addDiePanel.setLayout(new FlowLayout());
        addDiePanel.setVisible(false);
        
        rollButton.addActionListener(new roll());
        editButton.addActionListener(new editDice());
        addButton.addActionListener(new addDie());
    }
    
    public class addDie implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            Integer dieType = (Integer) JOptionPane.showInputDialog(null, "What sided die?", "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            diceList.add(new Die(dieType));
            JLabel temp = new JLabel("d"+dieType.toString());
            diePanel.add(temp);
            labelList.add(temp);
            JLabel tempX = new JLabel("X");
            tempX.setForeground (Color.red);
            tempX.addMouseListener(new deleteMouse());
            tempX.setVisible(true);
            diePanel.add(tempX);
            labelXList.add(tempX);
            diePanel.revalidate();
            diePanel.repaint();
            MainWindow.refresh();
        }
    }
    
    public class roll implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
             new RollResults(diceList);
        }
    }
    
    public class editDice implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        { 
            deleteMode = deleteMode ? false : true;
   
            if (deleteMode){                
                addDiePanel.setVisible(true);
                for(JLabel labelX : labelXList)
                {
                    labelX.setVisible(true);
                }
                current.repaint();
                current.revalidate();
                MainWindow.refresh();
            } else if (!deleteMode){
                addDiePanel.setVisible(false);
                for(JLabel labelX : labelXList)
                {
                    labelX.setVisible(false);
                }
                diePanel.repaint();
                diePanel.revalidate();
                current.repaint();
                current.revalidate(); 
                MainWindow.refresh();
            }
            
        }
    }
    
    private class deleteMouse implements MouseListener
    {
        public void mouseClicked(MouseEvent e)
        {
            Object source = e.getSource();
            if (source instanceof JLabel)
            {
                int index = labelXList.indexOf(source);
                if (index != -1)
                {   
                    diePanel.remove(labelList.get(index));
                    diePanel.remove((JLabel) source);
                    diceList.remove(index);
                    labelList.remove(index);
                    labelXList.remove(index);
                    diePanel.revalidate();
                    diePanel.repaint();
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
