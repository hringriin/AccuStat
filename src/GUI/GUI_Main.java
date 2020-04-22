package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import AccuStat.Battery;
import AccuStat.Brand;
import AccuStat.Main;

public class GUI_Main
{
    private static final String version = "0.1";
    private static JMenuBar menubar = new JMenuBar();

    private static JMenu fileMenu = new JMenu("File");

    //JMenuItem openConnection = new JMenuItem("Edit Connection");
    private static JMenuItem closeWindow = new JMenuItem("Exit");

    private static JPanel panel = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();


    private static JFrame frame = new JFrame ( "AccuStat v" + version );

    private static JButton newBrand = new JButton ("New Battery Brand");
    private static JButton newAccu = new JButton ("New Battery");
    private static JButton button3 = new JButton ("Button 3");
    private static JButton button4 = new JButton ("Button 4");
    private static JButton button5 = new JButton ("Button 5");
    private static JButton button6 = new JButton ("Button 6");

    private static JLabel accuSelectLabel = new JLabel("Select Battery:");

    private static JComboBox<Battery> accuSelect = new JComboBox<Battery>();

    public static void startGUI ()
    {
        menubar.add(fileMenu);

        //fileMenu.add(openConnection);
        fileMenu.add(closeWindow);

        closeWindow.addActionListener(e -> System.exit(0));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);

        newBrand.addActionListener(e -> addBatteryBrand());
        newAccu.addActionListener(e -> addBattery());

        accuSelect.setSize(10, 10);

        frame.setVisible(true);

        panel.add(newBrand);
        panel.add(newAccu);

        panel2.add(button3);
        panel2.add(button4);

        button5.addActionListener(e -> debugMe() );

        panel3.add(button5);
        panel3.add(button6);

        panel4.add(accuSelectLabel);
        panel4.add(accuSelect);

        frame.getContentPane().add(BorderLayout.NORTH, menubar);
        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.getContentPane().add(BorderLayout.WEST, panel2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel3);
        frame.getContentPane().add(BorderLayout.CENTER, panel4);
    }

    public static void debugMe()
    {
        ArrayList<Brand> testBrands = Main.getBrands();

        System.out.println("NEW OUTPUT");

        for ( Brand b : testBrands )
        {
            System.out.println(b);
        }
    }

    public static boolean addToAccuList( Battery pBattery )
    {
        if ( pBattery != null )
        {
            accuSelect.addItem(pBattery);

            return true;
        }

        return false;
    }

    public static void addBatteryBrand()
    {
        GUI_AddBrand.openMe();
    }

    public static void addBattery()
    {
        GUI_AddBattery.openMe();
    }
}