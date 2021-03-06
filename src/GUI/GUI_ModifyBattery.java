package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AccuStat.Battery;
import AccuStat.Main;

/**
 * to be used to modify a battery
 *
 * @author Joschka Köster
 *
 */
public class GUI_ModifyBattery
{
    /**
     * the main dialog window in this class
     */
    private JDialog dialog;

    /**
     * the main panel, containing the forms
     */
    private JPanel panel = new JPanel ( new GridLayout(4, 2, 10, 10));

    /**
     * the lower panel, containing the close button
     */
    private JPanel downPanel = new JPanel();

    private JPanel northPanel = new JPanel();

    /**
     * labels for each form field, i.e. each line
     */
    private JLabel labelBrand = new JLabel("Select Brand: ");
    private JLabel labelType = new JLabel ("Select Type: ");
    private JLabel labelBattery = new JLabel ("Select Battery: ");
    private JLabel labelBrandItem = new JLabel();
    private JLabel labelTypeItem = new JLabel();

    /**
     * buttons for modifying the batteries
     */
    private JButton save = new JButton("New Battery");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");
    private JButton refresh = new JButton("Refresh");

    /**
     * contains all the batteries
     */
    private JComboBox<Battery> batteries = new JComboBox<Battery>();

    /**
     * enter a battery name here ... or not?
     */
    private JTextField textfield = new JTextField(15);

    /**
     * new dialog windows opened from here to actually add a battery. removing works from this dialog.
     */
    private GUI_AddBattery guiAddBattery = new GUI_AddBattery(this.dialog, this);

    /**
     * GUI Class
     */
    @SuppressWarnings("unused")
    private GUI_Main GUI;

    /**
     * the main constructor, taking a JFrame to be locked. does not work, yet, though.
     *
     * @param pFrame the JFrame to be locked while this dialog remains open
     */
    public GUI_ModifyBattery(JFrame pFrame, GUI_Main pGUI)
    {
        this.GUI = pGUI;
        this.dialog = new JDialog(pFrame);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.save.addActionListener(e -> this.openAddBattery());
        this.remove.addActionListener(e -> remove());
        this.refresh.addActionListener(e -> this.refresh());

        this.cancel.addActionListener(e -> this.closeMe());

        dialog.setSize(320, 250);

        this.textfield.setSize(300, 100);

        this.panel.add(this.labelBattery);
        this.panel.add(this.batteries);

        this.panel.add(this.labelBrand);
        this.panel.add(this.labelBrandItem);

        this.panel.add(this.labelType);
        this.panel.add(this.labelTypeItem);

        this.panel.add(this.save);
        this.panel.add(this.remove);

        this.downPanel.add(cancel);

        this.northPanel.add(new JLabel("Modify Batteries"));
        this.northPanel.add(this.refresh);

        this.dialog.getContentPane().add(BorderLayout.NORTH, this.northPanel);
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
    }

    private void openAddBattery()
    {
        this.closeMe();
        this.guiAddBattery.openMe();
    }

    /**
     * set this dialog visible
     */
    public void openMe()
    {
        this.dialog.setVisible(true);
        Main.populateComboBoxes();
    }

    /**
     * set this dialog invisible
     */
    public void closeMe()
    {
        Main.populateComboBoxes();
        this.dialog.setVisible(false);
    }

    /**
     * remove a battery from the list
     */
    public void remove()
    {
        Main.removeBattery((Battery) this.batteries.getSelectedItem());
        Main.populateComboBoxes();
    }

    /**
     * populate the combobox with items from the arraylist in the main class and method
     */
    public void populate()
    {
        this.batteries.removeAllItems();
        this.labelBrandItem.setText("");
        this.labelTypeItem.setText("");

        ArrayList<Battery> battList = Main.get_Batteries();

        for ( Battery b : battList )
            this.batteries.addItem(b);

        this.guiAddBattery.populate();
    }

    public void populate(Battery pBattery, GUI_ModifyBattery pGUI)
    {
        this.labelBrandItem.setText(pBattery.getBrand().toString());
        this.labelTypeItem.setText(pBattery.getType().toString());
    }

    public void refresh()
    {
        Battery bat = (Battery) this.batteries.getSelectedItem();

        if ( bat == null )
            return;

        this.labelBrandItem.setText(bat.getBrand().toString());
        this.labelTypeItem.setText(bat.getType().toString());
    }
}
