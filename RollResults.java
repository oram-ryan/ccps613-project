import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.font.*;

public class RollResults
{
    JDialog rollDialog = new JDialog(MainWindow.getFrame(),"Roll Results",true);
    
    public RollResults(ArrayList<Die> diceList,Group current)
    {
        rollDialog.setLayout(new BoxLayout(rollDialog.getContentPane(), BoxLayout.Y_AXIS));
        JPanel results = new JPanel();
        rollDialog.add(results);
        
        Box rollResults1 = Box.createVerticalBox();
        Box rollResults2 = Box.createVerticalBox();
        
        rollResults1.createHorizontalStrut(20);
        
        results.add(rollResults1);
        results.add(rollResults2);

        JLabel labelType = new JLabel("Die");
        Font font = labelType.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        labelType.setFont(font.deriveFont(attributes));
        rollResults1.add(labelType);

        JLabel labelResult = new JLabel("Result");
        labelResult.setFont(font.deriveFont(attributes));
        rollResults2.add(labelResult);
        
        for (Die d : diceList)
        {
            rollResults1.add(new JLabel("d"+Integer.toString(d.getSides())));
        }
        for (Die d : diceList)
        {
            rollResults2.add(new JLabel(Integer.toString(d.roll())));
        }
        
        JPanel donePanel = new JPanel();
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new done());
        donePanel.add(doneButton);
        rollDialog.add(donePanel);
        
        rollDialog.pack();
        rollDialog.setLocationRelativeTo(current);
        rollDialog.setVisible(true);
    }
    
    public class done implements ActionListener 
    {
        public void actionPerformed(ActionEvent ae)
        {
            rollDialog.dispose();
        }
    }
}
