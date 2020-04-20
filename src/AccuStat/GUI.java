package AccuStat;

import javax.swing.*;
import java.awt.*;

public class GUI
{
    public static void startGUI ()
    {
        JMenuBar menubar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem openConnection = new JMenuItem("Edit Connection");
        JMenuItem closeWindow = new JMenuItem("Exit");

        menubar.add(fileMenu);

        fileMenu.add(openConnection);
        fileMenu.add(closeWindow);

        closeWindow.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();


        JFrame frame = new JFrame ( "My first GUI" );
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640, 400);


        JButton button1 = new JButton ("Button 1");
        JButton button2 = new JButton ("Button 2");
        JButton button3 = new JButton ("Button 3");
        JButton button4 = new JButton ("Button 4");
        JButton button5 = new JButton ("Button 5");
        JButton button6 = new JButton ("Button 6");
        JButton button7 = new JButton ("Button 7");
        JButton button8 = new JButton ("Button 8");

        frame.setVisible(true);

        panel.add(button1);
        panel.add(button2);
        button2.setEnabled(false);

        panel2.add(button3);
        panel2.add(button4);

        panel3.add(button5);
        panel3.add(button6);

        panel4.add(button7);
        panel4.add(button8);

        frame.getContentPane().add(BorderLayout.NORTH, menubar);
        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.getContentPane().add(BorderLayout.WEST, panel2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel3);
        frame.getContentPane().add(BorderLayout.CENTER, panel4);

    }
}