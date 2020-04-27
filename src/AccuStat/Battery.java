package AccuStat;

import java.io.Serializable;
import java.text.DecimalFormat;
//import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
     * list of voltages with date of mesurement
     */
    //    private ArrayList<Entry<LocalDateTime,Double>> voltageList;
    private ArrayList<Metering> voltageList;

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
        this.voltageList = new ArrayList<Metering>();
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
        if ( this.getVoltages().size() > 0 )
            return this.getVoltages().get(this.getVoltages().size() - 1).getVoltage();

        return -1;
    }

    /**
     * Returns a copy of the voltages list
     *
     * @return list of voltages
     */
    public ArrayList<Metering> getVoltages()
    {
        return new ArrayList<Metering>(this.voltageList);
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
    @Deprecated
    public boolean setVoltage( final double pVoltage )
    {
        //        if ( pVoltage < 0 )
        //            return false;
        //
        //        if ( pVoltage > 1.6 )
        //            return false;
        //
        //        this.voltage = pVoltage;
        //        this.voltageList.add(new AbstractMap.SimpleEntry<LocalDateTime,Double>(LocalDateTime.now(), pVoltage));
        //
        //        return true;
        return false;
    }

    public boolean setMetering ( final double pVoltage, final String pState )
    {
        if ( pVoltage < 0 || pVoltage > 1.6 )
            return false;

        if ( pState == null || pState.equals("") )
            return false;

        return this.voltageList.add(new Metering(pVoltage, LocalDateTime.now(), pState));
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

    public LocalDateTime getDate()
    {
        if ( this.getVoltages().size() > 0 )
            return this.getVoltages().get(this.getVoltages().size() - 1).getDate();

        return null;
    }

    public String getDateString()
    {
        if ( this.getVoltages().size() > 0 )
            return Main.dtf.format(this.getVoltages().get(this.getVoltages().size() - 1).getDate());

        return null;
    }

    public String getStatus()
    {
        if ( this.getVoltages().size() > 0 )
            return this.getVoltages().get(this.getVoltages().size() - 1).getState();

        return null;
    }

    @Deprecated
    public void setStatus(String pStatus)
    {
        return;
    }

    public String listToString()
    {
        String ret = "";

        for ( Metering m : this.getVoltages() )
        {
            ret += "\n\t\t";
            ret += m.getVoltage();
            ret += " V";
        }

        return ret;
    }

    public String listVoltages()
    {
        String ret = "";

        for (Metering m : this.getVoltages() )
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            ret += dtf.format(m.getDate())
                    + ":\t"
                    + new DecimalFormat("0.000").format(m.getVoltage())
                    + " V\n";
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
