package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import AccuStat.Battery;
import AccuStat.Main;

public class GUI_Main
{
    /**
     * main JFrame to show the main window
     */
    private JFrame frame = new JFrame("AccuStat " + Main.getVersionNumber());

    /**
     * the menubar, containing menus like "File", "Edit", "View", etc.
     */
    private JMenuBar menubar = new JMenuBar();

    /**
     * a menu
     */
    private JMenu fileMenu = new JMenu("File");

    /**
     * a menu item, responsible for closing the application properly
     */
    private JMenuItem closeWindow = new JMenuItem("Exit");

    /**
     * the panel containing top parts, like the combobox for the battery list
     */
    private JPanel panelNorth = new JPanel();

    /**
     * the panel containg extra buttons
     */
    private JPanel panelSouth = new JPanel();

    /**
     * the panel containing nothing (for now)
     */
    private JPanel panelWest = new JPanel();

    /**
     * the panel containing the buttons to modify brands, batteries and types
     */
    private JPanel panelEast = new JPanel();

    /**
     * this panel will contain the main view, statistics and so on
     */
    private JPanel panelCenter = new JPanel();

    /**
     * buttons to open a modify window for brands, batteries and types
     */
    private JButton modifyBrand = new JButton ("Modify Brands");
    private JButton modifyAccu = new JButton ("Modify Batteries");
    private JButton modifyType = new JButton ("Modify Types");

    /**
     * the label for the select battery combobox
     */
    private JLabel accuSelectLabel = new JLabel("Select Battery: ");

    /**
     * the battery combobox, containing all added batteries to select from and to show their stats in the center panel
     */
    private JComboBox<Battery> accuSelect = new JComboBox<Battery>();

    /**
     * dialog windows to be opened via the respective buttons (see above)
     */
    private GUI_ModifyBrand GUI_addBrand = new GUI_ModifyBrand(this.frame);
    private GUI_ModifyBattery GUI_addBattery = new GUI_ModifyBattery(this.frame);
    private GUI_ModifyType GUI_addType = new GUI_ModifyType(this.frame);

    /**
     * constructor, basically setting up the window and doing layout stuff.
     */
    public GUI_Main ()
    {
        this.menubar.add(fileMenu);

        //this.fileMenu.add(openConnection);
        this.fileMenu.add(closeWindow);

        this.closeWindow.addActionListener(e -> System.exit(0));

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(1000, 500);

        this.modifyBrand.addActionListener(e -> this.GUI_addBrand.openMe());
        this.modifyAccu.addActionListener(e -> this.GUI_addBattery.openMe());
        this.modifyType.addActionListener(e -> this.GUI_addType.openMe());

        this.frame.setVisible(true);

        this.panelEast.add(modifyType);
        this.panelEast.add(modifyBrand);
        this.panelEast.add(modifyAccu);

        this.panelNorth.add(accuSelectLabel);
        this.panelNorth.add(accuSelect);

        this.frame.getContentPane().add(BorderLayout.NORTH, panelNorth);
        this.frame.getContentPane().add(BorderLayout.EAST, panelEast);
        this.frame.getContentPane().add(BorderLayout.WEST, panelWest);
        this.frame.getContentPane().add(BorderLayout.SOUTH, panelSouth);
        this.frame.getContentPane().add(BorderLayout.CENTER, panelCenter);

        this.frame.setJMenuBar(menubar);
    }
}