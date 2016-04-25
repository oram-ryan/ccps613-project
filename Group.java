import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Group extends JPanel
{
    private Group current = this;
    private boolean deleteMode = false;
    private final static int MAXDICE = 14;
    
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
        diePanel.setPreferredSize(new Dimension(475,25));
        
        this.add(addDiePanel);
        addDiePanel.setLayout(new FlowLayout());
        addDiePanel.setVisible(false);
        
        rollButton.addActionListener(new roll());
        editButton.addActionListener(new editDice());
        addButton.addActionListener(new addDie());

        rollButton.setEnabled(false);
    }
    
    public class addDie implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            Integer dieType = (Integer) JOptionPane.showInputDialog(null, "What sided die?", "Die Size Selection", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (dieType == null) return;
            
            diceList.add(new Die(dieType));
            JLabel temp = new JLabel("d"+dieType.toString());
            diePanel.add(temp);
            labelList.add(temp);
            
            if (diceList.size() == 1) rollButton.setEnabled(true);
            
            if (diceList.size() >= MAXDICE) addButton.setEnabled(false);
            
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
                rollButton.setVisible(false);
                editButton.setText("Exit Edit");
                addDiePanel.setVisible(true);
                
                for(JLabel labelX : labelXList)
                {
                    labelX.setVisible(true);
                }
                
                current.repaint();
                current.revalidate();
                MainWindow.refresh();
            } else if (!deleteMode){
                rollButton.setVisible(true);
                editButton.setText("Edit");
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
                    
                    if (diceList.size() == 0) rollButton.setEnabled(false);
                    else if (diceList.size() == MAXDICE - 1) addButton.setEnabled(true);
                    
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
