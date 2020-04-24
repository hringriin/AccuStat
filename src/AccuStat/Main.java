package AccuStat;

import java.util.ArrayList;

import GUI.GUI_Main;

/**
 * AccuStat
 *
 * This class contains all the important lists of objects
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
     *
     * It is unused, for now, but it does stuff.
     */
    @SuppressWarnings("unused")
    private static GUI_Main GUI = new GUI_Main();

    /**
     * Main method and first to execute.
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

        BRANDS.add(new Brand("Varta"));
    }

    /**
     * @return all the batteries in the list as a list
     */
    public static ArrayList<Battery> get_Batteries()
    {
        return new ArrayList<Battery>(BATTERIES);
    }

    /**
     * @return all the brands in the list as a list
     */
    public static ArrayList<Brand> get_Brands()
    {
        if ( BRANDS.size() > 0 )
            return new ArrayList<Brand>(BRANDS);

        throw new IllegalArgumentException("HIER HAETTE ICH NICHT ANKOMMEN SOLLEN");
    }

    /**
     * @return all the types in the list as a list
     */
    public static ArrayList<Type> get_Types()
    {
        return new ArrayList<Type>(TYPES);
    }

    /**
     * @return the version number of this programm. let's see how useful this might be.
     */
    public static String getVersionNumber()
    {
        return versionNumber;
    }

    /**
     * @param pBrand the new Brand name to be added to the list
     */
    public static void newBrand( String pBrand )
    {
        if ( pBrand.equals("") || pBrand == null )
            throw new IllegalArgumentException("Brand name must not be empty!");

        BRANDS.add(new Brand(pBrand));
    }

    /**
     * @param pBrand the brand to be removed from the list
     */
    public static void removeBrand ( Brand pBrand )
    {
        BRANDS.remove(pBrand);
    }

    /**
     * @param pType the new type name to be added to the list
     */
    public static void newType ( String pType )
    {
        if ( pType.equals("") || pType == null )
            throw new IllegalArgumentException("Type name must not be empty!");

        TYPES.add(new Type(pType));
    }

    /**
     * @param pType the type to be removed from the list
     */
    public static void removeType ( Type pType )
    {
        TYPES.remove(pType);
    }

    /**
     * add a new battery to the list
     *
     * @param pBattery the battery name or identifier
     * @param pType the type of the battery
     * @param pBrand the brand of the battery
     */
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

    /**
     * @param pBattery the battery to be removed from the list
     */
    public static void removeBattery ( Battery pBattery )
    {
        BATTERIES.remove(pBattery);
    }

    public static void populateComboBoxes()
    {
        GUI.populateAll();
    }
}
