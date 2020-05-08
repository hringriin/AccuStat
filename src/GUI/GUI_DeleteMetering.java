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
import javax.swing.border.EmptyBorder;

import AccuStat.Battery;
import AccuStat.Main;
import AccuStat.Metering;

public class GUI_DeleteMetering
{
    /**
     * main dialog of this class
     */
    private JDialog dialog;

    /**
     * main and lower panel (see GUI_ModifyBrand.java)
     */
    private JPanel panel = new JPanel ( new GridLayout(2, 2, 10, 10));
    private JPanel downPanel = new JPanel();

    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");
    private JButton refresh = new JButton("Refresh");

    private JComboBox<Metering> meterings = new JComboBox<Metering>();
    private JComboBox<Battery> batteries = new JComboBox<Battery>();

    private JLabel labelBat = new JLabel("Battery: ");
    private JLabel labelMet = new JLabel("Metering: ");

    public GUI_DeleteMetering(JFrame pFrame)
    {
        this.dialog = new JDialog(pFrame);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.remove.addActionListener(e -> this.removeMetering());
        this.cancel.addActionListener(e -> this.cancel());
        this.refresh.addActionListener(e -> this.refresh());

        dialog.setSize(640, 200);

        this.panel.add(this.labelBat);
        this.panel.add(this.batteries);
        this.panel.add(this.labelMet);
        this.panel.add(this.meterings);

        this.downPanel.add(this.refresh);
        this.downPanel.add(this.remove);
        this.downPanel.add(this.cancel);

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("New Metering")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
    }

    public void openMe()
    {
        Main.populateComboBoxes();
        this.dialog.setVisible(true);
    }

    public void closeMe()
    {
        this.dialog.setVisible(false);
    }

    public void cancel()
    {
        this.meterings.setSelectedItem(null);
        this.closeMe();
    }

    public void removeMetering()
    {
        Battery b = (Battery) this.batteries.getSelectedItem();
        b.removeMetering((Metering) this.meterings.getSelectedItem());

        this.refresh();
    }

    public void populate()
    {
        this.batteries.removeAllItems();

        ArrayList<Battery> battList = Main.get_Batteries();

        for ( Battery b : battList )
            this.batteries.addItem(b);

        //        this.refresh();
    }

    public void refresh()
    {
        this.meterings.removeAllItems();

        Battery b = (Battery) this.batteries.getSelectedItem();
        ArrayList<Metering> metList = b.getMeterings();

        for ( Metering m : metList )
            this.meterings.addItem(m);
    }
}
