package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AccuStat.Brand;
import AccuStat.Main;

public class GUI_AddBattery
{
    private static JFrame frame = new JFrame ( "Add Battery" );

    private static JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    private static JPanel downPanel = new JPanel();

    private static JLabel labelID = new JLabel("Battery ID (Name): ");
    private static JLabel labelType = new JLabel("Type: ");
    private static JLabel labelBrand = new JLabel("Brand: ");

    private static JComboBox<String> types = new JComboBox<String>();
    private static JComboBox<Brand> brands = new JComboBox<Brand>();


    private static JButton save = new JButton("Save");
    private static JButton cancel = new JButton("Cancel");

    private static JTextField textfieldID = new JTextField(10);

    public static void openMe()
    {
        for ( Brand b : Main.getBrands() )
        {
            brands.addItem(b);
        }

        for ( String s : Main.getTypes() )
        {
            types.addItem(s);
        }

        save.addActionListener(e -> save());
        cancel.addActionListener(e -> frame.dispose());

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 200);

        textfieldID.setSize(300, 100);

        panel.add(labelID);
        panel.add(textfieldID);

        panel.add(labelType);
        panel.add(types);

        panel.add(labelBrand);
        panel.add(brands);

        panel.setBorder(new EmptyBorder(10,10,10,10));

        downPanel.add(save);
        downPanel.add(cancel);

        frame.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Add Battery")));
        frame.getContentPane().add(panel);
        frame.getContentPane().add(BorderLayout.SOUTH, downPanel);

        frame.setVisible(true);
    }

    public static void save()
    {
        if ( textfieldID.getText().equals("") || textfieldID.getText() == null )
            throw new IllegalArgumentException ("ID Label must not be empty");

        if ( types.getSelectedItem().equals("AA") )
        {

        }
        else if ( types.getSelectedItem().equals("AAA") )
        {
            //TODO
        }

        frame.dispose();
    }
}