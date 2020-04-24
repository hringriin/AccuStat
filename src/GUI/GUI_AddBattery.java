package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AccuStat.Battery;
import AccuStat.Brand;
import AccuStat.Main;
import AccuStat.Type;

public class GUI_AddBattery
{
    /**
     * the main dialog window in this class
     */
    private JDialog dialog;

    private GUI_ModifyBattery GUI;

    /**
     * the main panel, containing the forms
     */
    private JPanel panel = new JPanel ( new GridLayout(3, 2, 10, 10));

    /**
     * the lower panel, containing the close button
     */
    private JPanel downPanel = new JPanel();

    /**
     * labels for each form field, i.e. each line
     */
    private JLabel labelBattery = new JLabel ("Select Battery: ");
    private JLabel labelBrand = new JLabel("Select Brand: ");
    private JLabel labelType = new JLabel ("Select Type: ");

    /**
     * comboboxes containing brands and types
     */
    private JComboBox<Brand> brands = new JComboBox<Brand>();
    private JComboBox<Type> types = new JComboBox<Type>();

    /**
     * buttons for modifying the batteries
     */
    private JButton save = new JButton("New Battery");
    private JButton cancel = new JButton("Close");

    /**
     * enter a battery name here
     */
    private JTextField textfield = new JTextField(15);

    public GUI_AddBattery (JDialog pDialog, GUI_ModifyBattery pGUI)
    {
        this.GUI = pGUI;
        this.dialog = new JDialog(pDialog);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.save.addActionListener(e -> this.save());

        this.cancel.addActionListener(e -> this.closeMe());

        dialog.setSize(320, 250);

        this.textfield.setSize(300, 100);

        this.panel.add(this.labelBattery);
        this.panel.add(this.textfield);

        this.panel.add(this.labelBrand);
        this.panel.add(this.brands);

        this.panel.add(this.labelType);
        this.panel.add(this.types);

        this.downPanel.add(this.save);
        this.downPanel.add(cancel);

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Add new Battery")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
    }

    private void save()
    {
        Main.newBattery(this.textfield.getText(),
                (Type) this.types.getSelectedItem(),
                (Brand) this.brands.getSelectedItem());

        this.textfield.setText("");

        ArrayList<Battery> batts = Main.get_Batteries();

        System.out.println("NEW OUTPUT");
        for ( Battery b : batts )
            System.out.println(b);

        Main.populateComboBoxes();
    }

    public void openMe()
    {
        this.dialog.setVisible(true);
        Main.populateComboBoxes();
    }

    public void closeMe()
    {
        this.dialog.setVisible(false);
        this.GUI.openMe();
    }

    public void populate()
    {
        this.brands.removeAllItems();
        this.types.removeAllItems();

        ArrayList<Brand> brandList = Main.get_Brands();
        ArrayList<Type> typeList = Main.get_Types();

        for ( Brand b : brandList )
            brands.addItem(b);

        for ( Type t : typeList )
            types.addItem(t);
    }
}
