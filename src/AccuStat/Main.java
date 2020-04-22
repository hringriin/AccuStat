package AccuStat;

import java.util.ArrayList;

import GUI.GUI_Main;

/**
 * AccuStat
 *
 * @author Joschka Köster
 * @version 0.1a
 * @since 0.1a
 */
public class Main {
    /**
     * Version number
     */
    private static String versionNumber = "v0.1a";

    /**
     * Date format
     */
    //private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * list of brands
     */
    private static ArrayList<Brand> BRANDS;

    /**
     * list of types, e.g. AA, AAA, ...
     */
    private static ArrayList<Type> TYPES;

    /**
     * List of Batteries
     */
    private static ArrayList<Battery> BATTERIES;

    /**
     * GUI Main, starting itself.
     */
    @SuppressWarnings("unused")
    private static GUI_Main GUI = new GUI_Main();

    /**
     * Does stuff ...
     *
     * @param args
     */
    public static void main(String args[])
    {
        BATTERIES = new ArrayList<Battery>();
        TYPES = new ArrayList<Type>();
        BRANDS = new ArrayList<Brand>();

        TYPES.add(new Type("AA"));
        TYPES.add(new Type("AAA"));

        BRANDS.add(new Brand("TEST-Brand"));
    }

    public static ArrayList<Battery> get_Batteries()
    {
        return new ArrayList<Battery>(BATTERIES);
    }

    public static ArrayList<Brand> get_Brands()
    {
        if ( BRANDS.size() > 0 )
            return new ArrayList<Brand>(BRANDS);

        throw new IllegalArgumentException("HIER HAETTE ICH NICHT ANKOMMEN SOLLEN");
    }

    public static ArrayList<Type> get_Types()
    {
        return new ArrayList<Type>(TYPES);
    }

    public static String getVersionNumber()
    {
        return versionNumber;
    }

    public static void newBrand( String pBrand )
    {
        if ( pBrand.equals("") || pBrand == null )
            throw new IllegalArgumentException("Brand name must not be empty!");

        BRANDS.add(new Brand(pBrand));
    }

    public static void removeBrand ( Brand pBrand )
    {
        if ( pBrand == null )
            throw new IllegalArgumentException("Brand name to be deleted must not be empty or null!");

        BRANDS.remove(pBrand);
    }

    public static void newType ( String pType )
    {
        if ( pType.equals("") || pType == null )
            throw new IllegalArgumentException("Type name must not be empty!");

        TYPES.add(new Type(pType));
    }

    public static void removeType ( Type pType )
    {
        if ( pType == null )
            throw new IllegalArgumentException("Type name to be deleted must not be empty or null!");

        TYPES.remove(pType);
    }

    public static void newBattery ( String pBattery, Type pType, Brand pBrand )
    {
        if ( pBattery.equals("") || pBattery == null )
            throw new IllegalArgumentException("Battery name must not be empty!");

        if ( pType == null )
            throw new IllegalArgumentException("Type must not be empty!");

        if ( pBrand == null )
            throw new IllegalArgumentException("Brand must not be empty!");

        BATTERIES.add(new Battery(pBattery, pType, pBrand));
    }

    public static void removeBattery ( Battery pBattery )
    {
        if ( pBattery == null )
            throw new IllegalArgumentException("Battery name to be deleted must not be empty or null!");

        BATTERIES.remove(pBattery);
    }
}
