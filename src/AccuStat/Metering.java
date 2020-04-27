package AccuStat;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.swing.JFrame;

public class Metering implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -3334049189103951084L;

    private double voltage;
    private LocalDateTime date;
    private String state;

    public Metering ( double pVoltage, LocalDateTime pDate, String pState)
    {
        if ( pVoltage < 0 )
            Main.throwError(new JFrame(), "Invalid voltage!\nCannot be lower than 0!");

        if ( pVoltage > 1.6 )
            Main.throwError(new JFrame(), "Invalid voltage!\nCannot be higher than 1.6!");

        this.voltage = pVoltage;
        this.date = pDate;
        this.state = pState;
    }

    public double getVoltage()
    {
        return this.voltage;
    }

    public LocalDateTime getDate()
    {
        return this.date;
    }

    public String getState()
    {
        return this.state;
    }

    @Override
    public String toString()
    {
        return Main.dtf.format(this.getDate())
                + "("
                + this.getState()
                + "): "
                + this.getVoltage()
                + " V";
    }
}