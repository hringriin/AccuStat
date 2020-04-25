package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AccuStat.Battery;

public class GUI_NewMetering
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
    private JLabel labelBattery = new JLabel ("Select Battery: ");
    private JLabel labelVoltage = new JLabel ("Voltage: ");
    private JLabel labelState = new JLabel ("State: ");

    /**
     * buttons to modify the list and close the dialog
     */
    private JButton save = new JButton("Add");
    private JButton remove = new JButton("Remove");
    private JButton cancel = new JButton("Close");

    private JTextField textVoltage = new JTextField();
    private JTextField textState = new JTextField();

    /**
     * battery list
     */
    private JComboBox<Battery> batteries = new JComboBox<Battery>();

    /**
     * voltage
     */
    private double voltage;

    private String state;

    public GUI_NewMetering ( JFrame pFrame )
    {
        this.dialog = new JDialog(pFrame);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.save.addActionListener(e -> this.save());
        this.cancel.addActionListener(e -> this.cancel());

        dialog.setSize(320, 250);

        this.panel.add(this.labelBattery);
        this.panel.add(this.batteries);

        this.panel.add(this.labelState);
        this.panel.add(this.textState);

        this.panel.add(this.labelVoltage);
        this.panel.add(this.textVoltage);

        this.downPanel.add(this.save);
        this.downPanel.add(this.cancel);

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("New Metering")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);
    }

    public void openMe()
    {
        this.dialog.setVisible(true);
    }

    public void closeMe()
    {
        this.dialog.setVisible(false);
    }

    public void save()
    {
        Battery bat = (Battery) this.batteries.getSelectedItem();

        try
        {
            bat.setVoltage(Double.parseDouble(this.textVoltage.getText()));
        }
        catch (Exception e)
        {

        }

        bat.setStatus(this.textState.getText());
    }

    public void cancel()
    {
        this.textState.setText("");
        this.textVoltage.setText("");
        this.closeMe();
    }
}
