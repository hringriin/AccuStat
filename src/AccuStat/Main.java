package AccuStat;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * AccuStat
 *
 * @author Joschka Köster
 * @version 0.1a
 * @since 0.1a
 */
public class Main {

    /**
     * Date format
     */
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * List of AA Batteries
     */
    private static ArrayList<Battery> AA;

    /**
     * List of AAA Batteries
     */
    private static ArrayList<Battery> AAA;

    /**
     * Does stuff ...
     *
     * @param args
     */
    public static void main(String args[])
    {
        GUI.startGUI();

        AA = new ArrayList<Battery>();
        AAA = new ArrayList<Battery>();

        new_AA_Battery("01");
        new_AA_Battery("02");

        new_AAA_Battery("01");

        AA.get(0).setVoltage(1.234);
        AA.get(0).setVoltage(1.442);

        ArrayList<Entry<LocalDateTime,Double>> list = AA.get(0).getVoltages();

        for ( Entry<LocalDateTime, Double> i : list )
        {
            System.out.println(dtf.format(i.getKey()) + " --> " + i.getValue());
        }
    }

    /**
     * Create a new AA-battery and add it to the list
     */
    public static boolean new_AA_Battery(final String pName)
    {
        AA.add(new AA(pName));

        return true;
    }

    /**
     * Create a new AAA-Battery and add it to the list
     */
    public static boolean new_AAA_Battery(final String pName)
    {
        AAA.add(new AAA(pName));

        return true;
    }
}
