package AccuStat;

import javax.swing.JFrame;

public class Type
{
    /**
     * name of the brand
     */
    private final String name;

    /**
     * taking a string for the type name
     *
     * @param pName the name of the type
     */
    public Type ( final String pName )
    {
        if ( pName.equals("") || pName == null )
            Main.throwError(new JFrame(), "Type name must not be empty!");

        this.name = pName;
    }

    /**
     * @return the name of the type
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
