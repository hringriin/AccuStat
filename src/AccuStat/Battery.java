package AccuStat;

import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.Map.Entry;
//import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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
     * last known voltage
     */
    private double voltage;

    private ArrayList<Entry<LocalDateTime,Double>> voltageList;

    /**
     * Create a new battery
     *
     * @param pName The name/id of the battery
     * @param pType The type of the battery (i.e. AA, AAA, etc.)
     */
    public Battery (final String pName, final Brand pBrand)
    {
        if ( pName.equals("") || pName == null )
            throw new IllegalArgumentException("No name given");

        if ( pBrand == null )
            throw new IllegalArgumentException("No brand given");

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
    public void setVoltage( final double pVoltage )
    {
        if ( pVoltage < 0 )
            throw new IllegalArgumentException("Voltage cannot be lower than zero!");

        if ( pVoltage > 2 )
            throw new IllegalArgumentException("What kind of battery are you using? VOLTAGE TOO HIGH!");

        this.voltage = pVoltage;
        this.voltageList.add(new AbstractMap.SimpleEntry<LocalDateTime,Double>(LocalDateTime.now(), pVoltage));
    }
}
