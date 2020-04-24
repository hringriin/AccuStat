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

import AccuStat.Brand;
import AccuStat.Main;

/**
 * to be used to modify a brand
 *
 * @author Joschka Köster
 *
 */
public class GUI_ModifyBrand
{
    /**
     * the main dialog in this class
     */
    private JDialog dialog;

    /**
     * main and lower panel, containing the form fields and the close button
     */
    private JPanel panel = new JPanel ( new GridLayout(3, 2, 10, 10));
    private JPanel downPanel = new JPanel();

    /**
     * labels for form fields
     */
    private JLabel label = new JLabel("Enter Brand name: ");
    private JLabel labelBrand = new JLabel("Select Brand: ");

    /**
     * buttons to add, remove brands and close the dialog
     */
    private JButton save = new JButton("Add");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");

    /**
     * combobox containing all the brands
     */
    private JComboBox<Brand> brands = new JComboBox<Brand>();

    /**
     * textfield to enter a new brand name
     */
    private JTextField textfield = new JTextField(15);

    /**
     * main constructor, taking a pframe and
     *
     * @param pFrame the frame to be locked while this dialog remains open
     */
    public GUI_ModifyBrand (JFrame pFrame)
    {
        this.dialog = new JDialog(pFrame);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.save.addActionListener(e -> save());
        this.remove.addActionListener(e -> remove());

        this.cancel.addActionListener(e -> this.dialog.setVisible(false));

        dialog.setSize(320, 175);

        this.textfield.setSize(300, 100);

        this.panel.add(this.labelBrand);
        this.panel.add(this.brands);

        this.panel.add(this.label);
        this.panel.add(this.textfield);

        this.panel.add(this.save);
        this.panel.add(this.remove);

        this.downPanel.add(cancel);

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Modify Battery Brands")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
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
        this.dialog.setVisible(false);
    }

    /**
     * save a new brand to the list, delete the content of the textfield and repopulate the combobox
     */
    public void save()
    {
        Main.newBrand(this.textfield.getText());
        this.textfield.setText("");
        Main.populateComboBoxes();
    }

    /**
     * remove the selected brand from the list
     */
    public void remove()
    {
        Main.removeBrand((Brand) this.brands.getSelectedItem());
        Main.populateComboBoxes();
    }

    /**
     * populate combobox with items from the brand list from the main class/method
     */
    public void populate()
    {
        brands.removeAllItems();

        ArrayList<Brand> brandList = Main.get_Brands();

        for ( Brand b : brandList )
            brands.addItem(b);
    }
}