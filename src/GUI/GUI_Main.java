package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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
    private JPanel panelWest = new JPanel(new GridLayout(2, 1, 10, 10));

    /**
     * the panel containing the buttons to modify brands, batteries and types
     */
    private JPanel panelEast = new JPanel(new GridLayout(3, 1, 10, 10));

    /**
     * this panel will contain the main view, statistics and so on
     */
    private JPanel panelCenter = new JPanel(new GridLayout(8, 2, 10, 10));

    /**
     * buttons to open a modify window for brands, batteries and types
     */
    private JButton modifyBrand = new JButton ("Modify Brands");
    private JButton modifyAccu = new JButton ("Modify Batteries");
    private JButton modifyType = new JButton ("Modify Types");
    private JButton refreshAccu = new JButton ("Refresh");
    private JButton newMetering = new JButton ("New Metering");
    private JButton save = new JButton("Save");
    private JButton saveAs = new JButton("Save As");
    private JButton load = new JButton("Load");
    private JButton buttonNew = new JButton("New");
    private JButton overview = new JButton("Overview");

    /**
     * the label for the select battery combobox
     */
    private JLabel accuSelectLabel = new JLabel("Select Battery: ");

    private JLabel batteryName = new JLabel("Name: ");
    private JLabel batteryNameContent = new JLabel("");

    private JLabel batteryState = new JLabel("Current State: ");
    private JLabel batteryStateContent = new JLabel("");

    private JLabel batteryVoltage = new JLabel("Current Voltage: ");
    private JLabel batteryVoltageContent = new JLabel("");

    private JLabel batteryDate = new JLabel ("Last metered: ");
    private JLabel batteryDateContent = new JLabel ("");

    private JLabel batteryType = new JLabel("type: ");
    private JLabel batteryTypeContent = new JLabel("");

    private JLabel batteryBrand = new JLabel("Brand: ");
    private JLabel batteryBrandContent = new JLabel("");

    private JLabel lastVoltages = new JLabel("Last Voltages: ");


    private JTextPane lastVoltagesList = new JTextPane();
    private JScrollPane scrollpane = new JScrollPane(lastVoltagesList);

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
        this.lastVoltagesList.setEditable(false);

        this.menubar.add(this.fileMenu);

        //this.fileMenu.add(openConnection);
        this.fileMenu.add(this.closeWindow);

        this.closeWindow.addActionListener(e -> System.exit(0));

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(1250, 800);

        this.modifyBrand.addActionListener(e -> this.GUI_modifyBrand.openMe());
        this.modifyAccu.addActionListener(e -> this.GUI_modifyBattery.openMe());
        this.modifyType.addActionListener(e -> this.GUI_modifyType.openMe());
        this.refreshAccu.addActionListener(e -> this.refresh());
        this.newMetering.addActionListener(e -> this.GUI_newMetering.openMe());
        this.buttonNew.addActionListener(e -> Main.allNew());
        this.overview.addActionListener(e -> new GUI_Overview(this.frame).openMe());
        this.save.addActionListener(e -> {
            try
            {
                Main.saveMyShit();
            } catch (IOException e1)
            {
                Main.throwError(this, e1.toString());
                e1.printStackTrace();
            }
        });

        this.load.addActionListener(e -> {
            try
            {
                Main.loadMyShit();
            } catch (IOException e1)
            {
                Main.throwError(this, e1.toString());
                e1.printStackTrace();
            }
        });

        this.saveAs.addActionListener(e -> {
            try
            {
                Main.saveMyShitAss();
            } catch (IOException e1)
            {
                Main.throwError(this.frame, e1.toString());
                e1.printStackTrace();
            }
        });

        this.frame.setVisible(true);

        this.panelEast.add(this.modifyType);
        this.panelEast.add(this.modifyBrand);
        this.panelEast.add(this.modifyAccu);

        this.panelNorth.add(this.accuSelectLabel);
        this.panelNorth.add(this.accuSelect);
        this.panelNorth.add(this.refreshAccu);
        this.panelNorth.add(this.overview);

        this.panelWest.add(this.newMetering);

        this.panelSouth.add(this.buttonNew);
        this.panelSouth.add(this.save);
        this.panelSouth.add(this.saveAs);
        this.panelSouth.add(this.load);

        this.panelCenter.add(batteryName);
        this.panelCenter.add(batteryNameContent);
        this.panelCenter.add(batteryState);
        this.panelCenter.add(batteryStateContent);
        this.panelCenter.add(batteryVoltage);
        this.panelCenter.add(batteryVoltageContent);
        this.panelCenter.add(batteryDate);
        this.panelCenter.add(batteryDateContent);
        this.panelCenter.add(batteryType);
        this.panelCenter.add(batteryTypeContent);
        this.panelCenter.add(batteryBrand);
        this.panelCenter.add(batteryBrandContent);
        this.panelCenter.add(lastVoltages);
        this.panelCenter.add(scrollpane);

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

        this.refresh();
    }

    public void populateAll()
    {
        this.populate();
        this.GUI_modifyBrand.populate();
        this.GUI_modifyType.populate();
        this.GUI_modifyBattery.populate();
        this.GUI_newMetering.populate();
    }

    public void refresh()
    {
        System.out.println("You pressed 'refresh'. Gratz!");
        Battery bat = (Battery) this.accuSelect.getSelectedItem();

        if ( bat == null )
        {
            this.batteryNameContent.setText("");
            this.batteryTypeContent.setText("");
            this.batteryBrandContent.setText("");
            this.batteryVoltageContent.setText("");
            this.batteryStateContent.setText("");
            this.batteryDateContent.setText("");
            this.lastVoltagesList.setText("");
        }
        else
        {
            try
            {
                this.batteryNameContent.setText(bat.getName());
                this.batteryTypeContent.setText(bat.getType().toString());
                this.batteryBrandContent.setText(bat.getBrand().toString());
                this.batteryVoltageContent.setText(String.valueOf(bat.getVoltage()) + " V");
                this.batteryStateContent.setText(bat.getStatus());
                this.batteryDateContent.setText(bat.getDateString());
                this.lastVoltagesList.setText(bat.listVoltages());
            }
            catch ( Exception e )
            {
                Main.throwError(this.frame, e.toString());
                e.printStackTrace();
            }
        }



    }

    public void throwError(JDialog GUI, String pString)
    {
        new GUI_Error(GUI, pString);
    }

    public void throwError (JFrame GUI, String pString)
    {
        new GUI_Error(GUI, pString);
    }

    public String saveGUI()
    {
        String ret = "";

        JFileChooser saveDialog = new JFileChooser();
        saveDialog.setDialogTitle("Save My Shit!");

        if ( saveDialog.showSaveDialog(this.frame) == JFileChooser.APPROVE_OPTION )
        {
            File file = saveDialog.getCurrentDirectory();
            ret = file.getAbsolutePath();
            System.out.println(ret);
        }
        else
            return null;

        return ret;
    }

    public String loadGUI()
    {
        String ret = "";

        JFileChooser loadDialog = new JFileChooser();
        loadDialog.setDialogTitle("Load My Shit!");

        if ( loadDialog.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION )
        {
            File file = loadDialog.getCurrentDirectory();
            ret = file.getAbsolutePath();
            System.out.println(ret);
        }
        else
            return null;

        return ret;
    }
}