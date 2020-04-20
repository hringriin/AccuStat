package AccuStat;

import javax.swing.*;
import java.awt.*;

public class GUI
{
    public static void main ( String args[] )
    {
        JFrame frame = new JFrame ( "My first GUI" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 400);


        JButton button1 = new JButton ("Button 1");
        JButton button2 = new JButton ("Button 2");
        frame.getContentPane().add(BorderLayout.WEST, button1);
        frame.getContentPane().add(BorderLayout.EAST, button2);
        frame.setVisible(true);
    }
}