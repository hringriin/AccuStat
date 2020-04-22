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

public class GUI_ModifyBrand
{
    private JDialog dialog;

    private JPanel panel = new JPanel ( new GridLayout(3, 2, 10, 10));
    private JPanel downPanel = new JPanel();

    private JLabel label = new JLabel("Enter Brand name: ");
    private JLabel labelBrand = new JLabel("Select Brand: ");

    private JButton save = new JButton("Add");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");

    private JComboBox<Brand> brands = new JComboBox<Brand>();

    private JTextField textfield = new JTextField(15);

    public GUI_ModifyBrand (JFrame pFrame, GUI_Main pGUI)
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
        Main.newBrand(this.textfield.getText());
        this.textfield.setText("");
        this.populateComboBox();
    }

    public void remove()
    {
        Main.removeBrand((Brand) this.brands.getSelectedItem());
        this.populateComboBox();
    }

    private void populateComboBox()
    {
        if ( brands.getItemCount() > 0 )
            brands.removeAllItems();

        ArrayList<Brand> brandList = Main.get_Brands();

        if ( brandList.size() > 0 )
        {
            for ( Brand b : brandList )
            {
                brands.addItem(b);
            }
        }
    }
}