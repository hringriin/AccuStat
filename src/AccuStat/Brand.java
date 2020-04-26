package AccuStat;

import java.io.Serializable;

import javax.swing.JFrame;

public class Brand implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -6685595172207744195L;
    /**
     * name of the brand
     */
    private final String name;

    /**
     * constructor, taking a name as parameter to add it as the brand's name
     *
     * @param pName the brand name
     */
    public Brand ( final String pName )
    {
        if ( pName.equals("") || pName == null )
            Main.throwError(new JFrame(), "Brand name must not be empty!");

        this.name = pName;
    }

    /**
     * @return the brand name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * return something useful
     */
    @Override
    public String toString()
    {
        return this.getName();
    }
}
