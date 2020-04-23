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

import AccuStat.Main;
import AccuStat.Type;

/**
 * dialog to modify the type list
 *
 * @author hringriin
 *
 */
public class GUI_ModifyType
{
    /**
     * main dialog of this class
     */
    private JDialog dialog;

    /**
     * main and lower panel (see GUI_ModifyBrand.java)
     */
    private JPanel panel = new JPanel ( new GridLayout(5, 2, 10, 10));
    private JPanel downPanel = new JPanel();

    /**
     * labels for each form field (line)
     */
    private JLabel labelName = new JLabel("Enter Type name: ");
    private JLabel labelType = new JLabel ("Select Type: ");

    /**
     * buttons to modify the list and close the dialog
     */
    private JButton save = new JButton("Add");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");

    /**
     * combobox containing all the types
     */
    private JComboBox<Type> types = new JComboBox<Type>();

    /**
     * textfield to add a new type name
     */
    private JTextField textfield = new JTextField(15);

    /**
     * main constructor
     *
     * @param pFrame the frame to be locked while this dialog remains open
     */
    public GUI_ModifyType(JFrame pFrame)
    {
        this.dialog = new JDialog(pFrame);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.save.addActionListener(e -> save());
        this.remove.addActionListener(e -> remove());

        this.cancel.addActionListener(e -> this.dialog.setVisible(false));

        dialog.setSize(320, 250);

        this.textfield.setSize(300, 100);

        this.panel.add(this.labelType);
        this.panel.add(this.types);

        this.panel.add(this.labelName);
        this.panel.add(this.textfield);

        this.panel.add(this.save);
        this.panel.add(this.remove);

        this.downPanel.add(cancel);

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Modify Batteries")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
    }

    /**
     * set this dialog visible
     */
    public void openMe()
    {
        this.dialog.setVisible(true);
        this.populateComboBox();
    }

    /**
     * set this dialog invisible
     */
    public void closeMe()
    {
        this.dialog.setVisible(false);
    }

    /**
     * save a new type to the list
     */
    public void save()
    {
        Main.newType(this.textfield.getText());
        this.textfield.setText("");
        this.populateComboBox();
    }

    /**
     * remove a type from the list
     */
    public void remove()
    {
        Main.removeType((Type) this.types.getSelectedItem());
        this.populateComboBox();
    }

    /**
     * populate the combobox with items from the list
     */
    private void populateComboBox()
    {
        types.removeAllItems();

        ArrayList<Type> typeList = Main.get_Types();

        for ( Type t : typeList )
            types.addItem(t);
    }
}