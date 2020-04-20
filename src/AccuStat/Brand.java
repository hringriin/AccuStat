package AccuStat;

public class Brand
{
    /**
     * name of the brand
     */
    private final String name;

    public Brand ( final String pName )
    {
        if ( pName.equals("") || pName == null )
            throw new IllegalArgumentException("Brand name must not be empty");

        this.name = pName;
    }
}
