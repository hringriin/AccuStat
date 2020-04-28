package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import AccuStat.Battery;
import AccuStat.Main;

public class GUI_Overview
{
    /**
     * main JFrame to show the main window
     */
    private JDialog dialog;

    private JButton close = new JButton("Close");
    private JButton all = new JButton ("Show All");
    private JButton charging = new JButton ("Show Charging");
    private JButton inUse = new JButton ("Show In-Use");
    private JButton stored = new JButton ("Show Stored");

    /**
     * the menubar, containing menus like "File", "Edit", "View", etc.
     */
    private JMenuBar menubar = new JMenuBar();

    private JPanel panelEast = new JPanel(new GridLayout(4, 1, 10, 10));

    private JTextPane textpane = new JTextPane();
    private JScrollPane scrollpane = new JScrollPane(this.textpane);

    /**
     * constructor, basically setting up the window and doing layout stuff.
     */
    public GUI_Overview (JFrame pFrame)
    {
        this.dialog = new JDialog(pFrame);
        this.textpane.setEditable(false);

        this.all.addActionListener(e -> this.populate(null));
        this.charging.addActionListener(e -> this.populate("Charging"));
        this.inUse.addActionListener(e -> this.populate("In Use"));
        this.stored.addActionListener(e -> this.populate("Stored"));

        this.panelEast.add(this.all);
        this.panelEast.add(this.charging);
        this.panelEast.add(this.inUse);
        this.panelEast.add(this.stored);

        this.dialog.setSize(800, 600);

        this.close.addActionListener(e -> this.dialog.dispose());

        this.dialog.getContentPane().add(BorderLayout.CENTER, this.scrollpane);
        this.dialog.getContentPane().add(BorderLayout.SOUTH, this.close);
        this.dialog.getContentPane().add(BorderLayout.EAST, this.panelEast);

        this.dialog.setJMenuBar(this.menubar);
    }

    public void openMe()
    {
        this.dialog.setVisible(true);
        this.populate(null);
    }

    public void closeMe()
    {
        this.dialog.dispose();
    }

    public void populate(String pStatus)
    {
        ArrayList<Battery> battList = Main.get_Batteries();

        String ret = "";

        for ( Battery b : battList )
        {
            if ( pStatus == null || b.getStatus() == null || b.getStatus().equals(pStatus) )
            {
                ret += b.toString()
                        + "\t\t"
                        + (b.getStatus() == null ? "" : b.getStatus())
                        + ": \t"
                        + new DecimalFormat("0.000").format(b.getVoltage())
                        + "\n";
            }
        }

        this.textpane.setText(ret);
        System.out.println(ret);
    }
}
