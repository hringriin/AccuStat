package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AccuStat.Brand;
import AccuStat.Main;

public class GUI_AddBrand
{
    private static JFrame frame = new JFrame ( "Add Battery Brand" );

    private static JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
    private static JPanel downPanel = new JPanel();

    private static JLabel label = new JLabel("Enter Brand name: ");
    private static JLabel labelBrand = new JLabel("Select Brand: ");

    private static JButton save = new JButton("Save");
    private static JButton remove = new JButton("Remove");
    private static JButton cancel = new JButton("Close");

    private static JComboBox<Brand> brands = new JComboBox<Brand>();

    private static JTextField textfield = new JTextField(15);

    public static void openMe()
    {
        populateBrandComboBox();

        panel.setBorder(new EmptyBorder(10,10,10,10));

        save.addActionListener(e -> save());
        remove.addActionListener(e -> remove());


        cancel.addActionListener(e -> frame.dispose());

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 200);

        textfield.setSize(300, 100);

        panel.add(labelBrand);
        panel.add(brands);

        panel.add(label);
        panel.add(textfield);

        panel.add(save);
        panel.add(remove);

        downPanel.add(cancel);

        frame.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Add Battery Brand")));
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, downPanel);

        frame.setVisible(true);
    }

    public static void save()
    {
        Main.newBrand(textfield.getText());
        textfield.removeAll();
        populateBrandComboBox();
    }

    public static void remove()
    {
        Main.removeBrand((Brand) brands.getSelectedItem());
        populateBrandComboBox();
    }

    public static void populateBrandComboBox()
    {
        brands.removeAllItems();

        ArrayList<Brand> brandList = Main.getBrands();

        if ( brandList.size() > 0 )
        {
            for ( Brand b : brandList )
            {
                brands.addItem(b);
            }
        }
    }
}