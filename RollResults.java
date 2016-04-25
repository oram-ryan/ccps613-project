import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RollResults
{
    public RollResults(ArrayList<Die> diceList)
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
        rollResults1.repaint();
        rollResults2.revalidate();
        rollResults2.repaint();
        rollFrame.pack();
        rollFrame.setVisible(true);  
    }
}
