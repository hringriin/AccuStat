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

public class GUI_ModifyType
{
    private JDialog dialog;

    private JPanel panel = new JPanel ( new GridLayout(5, 2, 10, 10));
    private JPanel downPanel = new JPanel();

    private JLabel labelName = new JLabel("Enter Type name: ");
    private JLabel labelType = new JLabel ("Select Type: ");

    private JButton save = new JButton("Add");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");

    private JComboBox<Type> types = new JComboBox<Type>();

    private JTextField textfield = new JTextField(15);

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

    public void openMe()
    {
        this.dialog.setVisible(true);
        this.populateComboBox();
    }

    public void closeMe()
    {
        this.dialog.setVisible(false);
    }

    public void save()
    {
        Main.newType(this.textfield.getText());
        this.textfield.setText("");
        this.populateComboBox();
    }

    public void remove()
    {
        Main.removeType((Type) this.types.getSelectedItem());
        this.populateComboBox();
    }

    private void populateComboBox()
    {
        types.removeAllItems();

        ArrayList<Type> typeList = Main.get_Types();

        for ( Type t : typeList )
            types.addItem(t);
    }
}