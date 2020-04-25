package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class GUI_Error
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

    private JButton close = new JButton ("Close");

    private JTextPane text = new JTextPane();

    public GUI_Error ( JDialog pDialog, String pText )
    {
        this.dialog = new JDialog(pDialog);
        this.initGUI(pText);
    }

    public GUI_Error (JFrame pFrame, String pText)
    {
        this.dialog = new JDialog(pFrame);
        this.initGUI(pText);
    }

    public void initGUI(String pString)
    {
        this.dialog.setSize(320, 250);
        this.text.setSize(300, 230);
        this.text.setEditable(false);

        this.panel.setBorder(new EmptyBorder(10,10,10,10));

        this.text.setText(pString);
        this.panel.add(this.text);

        this.close.addActionListener(e -> this.dialog.dispose());
        this.downPanel.add(this.close);

        this.dialog.getContentPane().add(BorderLayout.NORTH, new JPanel().add(new JLabel("Errör!")));
        this.dialog.getContentPane().add(BorderLayout.CENTER, this.text);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.downPanel);

        this.dialog.setVisible(true);
    }
}