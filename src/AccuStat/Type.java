package AccuStat;

public class Type
{
    /**
     * name of the brand
     */
    private final String name;

    public Type ( final String pName )
    {
        if ( pName.equals("") || pName == null )
            throw new IllegalArgumentException("Type name must not be empty");

        this.name = pName;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}