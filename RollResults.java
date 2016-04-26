import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.font.*;

public class RollResults
{
    public RollResults(ArrayList<Die> diceList,Group current)
    {
        JDialog rollDialog = new JDialog(MainWindow.getFrame(),"Roll Result",true);
        rollDialog.setLayout(new GridLayout(1,2));
        Box rollResults1 = Box.createVerticalBox();
        Box rollResults2 = Box.createVerticalBox();
        
        rollDialog.add(rollResults1);
        rollDialog.add(rollResults2);

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
        
        rollDialog.pack();
        rollDialog.setLocationRelativeTo(current);
        rollDialog.setVisible(true);
    }
}
