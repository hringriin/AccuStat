package AccuStat;

import java.io.Serializable;
//import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JFrame;

public class Battery implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 250077405993564588L;

    /**
     * id/number/name
     */
    private final String name;

    /**
     * brand of battery (duracell, panasonic, ...)
     */
    private final Brand brand;

    /**
     * type of battery, i.e. AA, AAA, etc
     */
    private final Type type;

    /**
     * last known voltage
     */
    private double voltage;

    /**
     * status of the battery, i.e. charing, in use, on hold, waiting, whatever
     */
    private String status;

    /**
     * list of voltages with date of mesurement
     */
    private ArrayList<Entry<LocalDateTime,Double>> voltageList;

    /**
     * design of battery (500 mAh, 1000 mAh, ...)
     */
    private String design;

    /**
     * Create a new battery
     *
     * @param pName The name/id of the battery
     * @param pType The type of the battery (i.e. AA, AAA, etc.)
     */
    public Battery (final String pName, final Type pType, final Brand pBrand)
    {
        if ( pName.equals("") || pName == null )
            Main.throwError(new JFrame(), "No name given!");

        if ( pBrand == null )
            Main.throwError(new JFrame(), "No brand given!");

        if ( pType == null )
            Main.throwError(new JFrame(), "No type given!");

        this.name = pName;
        this.brand = pBrand;
        this.type = pType;
        this.voltageList = new ArrayList<Entry<LocalDateTime,Double>>();
    }

    /**
     * @return the name/id of the battery
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return the design of the battery
     */
    public String getDesign()
    {
        return this.design;
    }

    /**
     * @return the current or last known voltage of the battery
     */
    public double getVoltage()
    {
        return this.voltage;
    }

    /**
     * Returns a copy of the voltages list
     *
     * @return list of voltages
     */
    public ArrayList<Entry<LocalDateTime,Double>> getVoltages()
    {
        return new ArrayList<Entry<LocalDateTime,Double>>(this.voltageList);
    }

    /**
     * the design is the power capacity, like 500 mAh or 1000 mAh
     *
     * @param pString the new design
     * @return if {@code pString} is valid, return true
     */
    public boolean setDesign ( final String pString )
    {
        if ( pString.equals("") || pString == null )
            Main.throwError(new JFrame(), "Design must not be null or empty!");

        this.design = pString;

        return true;
    }

    /**
     * Set the new current voltage for the battery
     *
     * @param pVoltage the new Voltage
     */
    public boolean setVoltage( final double pVoltage )
    {
        if ( pVoltage < 0 )
            return false;

        if ( pVoltage > 1.6 )
            return false;

        this.voltage = pVoltage;
        this.voltageList.add(new AbstractMap.SimpleEntry<LocalDateTime,Double>(LocalDateTime.now(), pVoltage));

        return true;
    }

    /**
     * @return the brand of the battery
     */
    public Brand getBrand ()
    {
        return this.brand;
    }

    /**
     * @return the type of the battery (AA, AAA, ...)
     */
    public Type getType()
    {
        return this.type;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String pStatus)
    {
        if (pStatus.equals("") || pStatus == null )
            Main.throwError(new JFrame(), "Status must not be null or empty!");

        this.status = pStatus;
    }

    private String listToString()
    {
        String ret = "";

        for ( Entry<LocalDateTime, Double> e : this.getVoltages() )
        {
            ret += "\n\t\t";
            ret += e.getValue();
            ret += " V";
        }

        return ret;
    }

    /**
     * return something useful
     */
    @Override
    public String toString()
    {
        return this.getBrand()
                + " ("
                + this.getType()
                + "): "
                + this.getName();
    }

    public String toStringLong()
    {
        return "\n\n"
                + this.toString()
                + "\n"
                + this.getStatus()
                + "\n\t"
                + "Last metered with: "
                + this.getVoltage()
                + " V"
                + "\n\t"
                + "Voltage List:"
                + this.listToString();
    }
}
