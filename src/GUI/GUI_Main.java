package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import AccuStat.Battery;
import AccuStat.Main;

public class GUI_Main
{
    private JFrame frame = new JFrame("AccuStat " + Main.getVersionNumber());

    private JMenuBar menubar = new JMenuBar();

    private JMenu fileMenu = new JMenu("File");

    private JMenuItem closeWindow = new JMenuItem("Exit");

    private JPanel panelNorth = new JPanel();
    private JPanel panelSouth = new JPanel();
    private JPanel panelWest = new JPanel();
    private JPanel panelEast = new JPanel();
    private JPanel panelCenter = new JPanel();

    private JButton modifyBrand = new JButton ("Modify Brands");
    private JButton modifyAccu = new JButton ("Modify Batteries");
    private JButton modifyType = new JButton ("Modify Types");

    private JLabel accuSelectLabel = new JLabel("Select Battery: ");

    private JComboBox<Battery> accuSelect = new JComboBox<Battery>();

    private GUI_ModifyBrand GUI_addBrand = new GUI_ModifyBrand(this.frame, this);
    private GUI_ModifyBattery GUI_addBattery = new GUI_ModifyBattery(this.frame);
    private GUI_ModifyType GUI_addType = new GUI_ModifyType(this.frame);

    public GUI_Main ()
    {
        this.menubar.add(fileMenu);

        //this.fileMenu.add(openConnection);
        this.fileMenu.add(closeWindow);

        this.closeWindow.addActionListener(e -> System.exit(0));

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(1000, 500);

        this.modifyBrand.addActionListener(e -> this.GUI_addBrand.openMe());
        this.modifyAccu.addActionListener(e -> this.GUI_addBattery.openMe());
        this.modifyType.addActionListener(e -> this.GUI_addType.openMe());

        this.frame.setVisible(true);

        this.panelEast.add(modifyType);
        this.panelEast.add(modifyBrand);
        this.panelEast.add(modifyAccu);

        this.panelNorth.add(accuSelectLabel);
        this.panelNorth.add(accuSelect);

        this.frame.getContentPane().add(BorderLayout.NORTH, panelNorth);
        this.frame.getContentPane().add(BorderLayout.EAST, panelEast);
        this.frame.getContentPane().add(BorderLayout.WEST, panelWest);
        this.frame.getContentPane().add(BorderLayout.SOUTH, panelSouth);
        this.frame.getContentPane().add(BorderLayout.CENTER, panelCenter);

        this.frame.setJMenuBar(menubar);
    }
}