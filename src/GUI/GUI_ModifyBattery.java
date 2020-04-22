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

public class GUI_ModifyBattery
{
    private JDialog dialog;

    private JPanel panel = new JPanel ( new GridLayout(4, 2, 10, 10));
    private JPanel downPanel = new JPanel();

    private JLabel labelName = new JLabel("Battery name: ");
    private JLabel labelBrand = new JLabel("Select Brand: ");
    private JLabel labelType = new JLabel ("Select Type: ");
    private JLabel labelBattery = new JLabel ("Select Battery: ");

    private JLabel labelBrandItem = new JLabel();
    private JLabel labelTypeItem = new JLabel();

    private JButton save = new JButton("New Battery");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");

    private JComboBox<Battery> batteries = new JComboBox<Battery>();

    private JTextField textfield = new JTextField(15);

    private GUI_AddBattery guiAddBattery = new GUI_AddBattery();

    public GUI_ModifyBattery(JFrame pFrame)
    {
        this.dialog = new JDialog(pFrame);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.save.addActionListener(e -> this.guiAddBattery.openMe());
        this.remove.addActionListener(e -> remove());

        this.cancel.addActionListener(e -> this.dialog.setVisible(false));

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

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Modify Batteries")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
    }

    public void openMe()
    {
        this.dialog.setVisible(true);
        this.populateBatteryComboBox();
    }

    public void closeMe()
    {
        this.dialog.setVisible(false);
    }

    public void remove()
    {
        Main.removeBattery((Battery) this.batteries.getSelectedItem());
        this.populateBatteryComboBox();
    }

    private void populateBatteryComboBox()
    {
        this.batteries.removeAllItems();

        ArrayList<Battery> battList = Main.get_Batteries();

        for ( Battery b : battList )
        {
            this.batteries.addItem(b);
        }
    }
}