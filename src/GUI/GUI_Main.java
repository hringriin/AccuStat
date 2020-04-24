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
    private JButton refreshAccu = new JButton ("Refresh");
    private JButton newMetering = new JButton ("New Metering");

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
    private GUI_ModifyBrand GUI_modifyBrand = new GUI_ModifyBrand(this.frame);
    private GUI_ModifyBattery GUI_modifyBattery = new GUI_ModifyBattery(this.frame, this);
    private GUI_ModifyType GUI_modifyType = new GUI_ModifyType(this.frame);
    private GUI_NewMetering GUI_newMetering = new GUI_NewMetering(this.frame);

    /**
     * constructor, basically setting up the window and doing layout stuff.
     */
    public GUI_Main ()
    {
        this.menubar.add(this.fileMenu);

        //this.fileMenu.add(openConnection);
        this.fileMenu.add(this.closeWindow);

        this.closeWindow.addActionListener(e -> System.exit(0));

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(1000, 500);

        this.modifyBrand.addActionListener(e -> this.GUI_modifyBrand.openMe());
        this.modifyAccu.addActionListener(e -> this.GUI_modifyBattery.openMe());
        this.modifyType.addActionListener(e -> this.GUI_modifyType.openMe());
        this.refreshAccu.addActionListener(e -> this.refresh());
        this.newMetering.addActionListener(e -> this.GUI_newMetering.openMe());

        this.frame.setVisible(true);

        this.panelEast.add(this.modifyType);
        this.panelEast.add(this.modifyBrand);
        this.panelEast.add(this.modifyAccu);

        this.panelNorth.add(this.accuSelectLabel);
        this.panelNorth.add(this.accuSelect);
        this.panelNorth.add(this.refreshAccu);

        this.frame.getContentPane().add(BorderLayout.NORTH, this.panelNorth);
        this.frame.getContentPane().add(BorderLayout.EAST, this.panelEast);
        this.frame.getContentPane().add(BorderLayout.WEST, this.panelWest);
        this.frame.getContentPane().add(BorderLayout.SOUTH, this.panelSouth);
        this.frame.getContentPane().add(BorderLayout.CENTER, this.panelCenter);

        this.frame.setJMenuBar(this.menubar);
    }

    public void populate()
    {
        this.accuSelect.removeAllItems();

        ArrayList<Battery> battList = Main.get_Batteries();

        for ( Battery b : battList )
            this.accuSelect.addItem(b);
    }

    public void populateAll()
    {
        this.populate();
        this.GUI_modifyBrand.populate();
        this.GUI_modifyType.populate();
        this.GUI_modifyBattery.populate();
    }

    public void refresh()
    {
        System.out.println("You pressed 'refresh'. Gratz!");
    }
}