package AccuStat;

//import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Battery {

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
    private final String type;

    /**
     * last known voltage
     */
    private double voltage;

    /**
     * list of voltages with date of mesurement
     */
    private ArrayList<Entry<LocalDateTime,Double>> voltageList;

    /**
     * Create a new battery
     *
     * @param pName The name/id of the battery
     * @param pType The type of the battery (i.e. AA, AAA, etc.)
     */
    public Battery (final String pName, final String pType, final Brand pBrand)
    {
        if ( pName.equals("") || pName == null )
            throw new IllegalArgumentException("No name given");

        if ( pBrand == null )
            throw new IllegalArgumentException("No brand given");

        if ( pType.equals("AA") || pType.equals("AAA") )
        {
            this.type = pType;
        }
        else
        {
            throw new IllegalArgumentException("Battery type unknown");
        }

        this.name = pName;
        this.brand = pBrand;
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

    public Brand getBrand ()
    {
        return this.brand;
    }

    public String getType()
    {
        return this.type;
    }

    @Override
    public String toString()
    {
        return this.getBrand()
                + " ("
                + this.getType()
                + "): "
                + this.getName();
    }
}
