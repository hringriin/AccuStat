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
     * Date format
     */
    //private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * list of brands
     */
    private static ArrayList<Brand> BRANDS;

    /**
     * list of types
     */
    private static ArrayList<String> TYPES;

    /**
     * List of AA Batteries
     */
    private static ArrayList<Battery> List_AA;

    /**
     * List of AAA Batteries
     */
    private static ArrayList<Battery> List_AAA;

    /**
     * Does stuff ...
     *
     * @param args
     */
    public static void main(String args[])
    {
        GUI_Main.startGUI();

        List_AA = new ArrayList<Battery>();
        List_AAA = new ArrayList<Battery>();
        TYPES = new ArrayList<String>();
        BRANDS = new ArrayList<Brand>();

        TYPES.add("AA");
        TYPES.add("AAA");
    }

    /**
     * Create a new AA-battery and add it to the list
     */
    public static boolean new_AA_Battery(final String pName, final String pType, final Brand pBrand)
    {
        if ( BRANDS.size() == 0 )
            throw new IllegalArgumentException("Brand list is empty."
                    + "Cannot add Battery without a brand to choose from."
                    + "Add a brand first.");

        if ( pBrand == null )
            throw new IllegalArgumentException("No brand name given!");

        AA bat = new AA(pName, pType, pBrand);
        List_AA.add(bat);
        GUI_Main.addToAccuList(bat);

        return true;
    }

    /**
     * Create a new AAA-Battery and add it to the list
     */
    public static boolean new_AAA_Battery(final String pName, final String pType, final Brand pBrand)
    {
        if ( BRANDS.size() == 0 )
            throw new IllegalArgumentException("Brand list is empty."
                    + "Cannot add Battery without a brand to choose from."
                    + "Add a brand first.");

        if ( pBrand == null )
            throw new IllegalArgumentException("No brand name given!");

        AAA bat = new AAA(pName, pType, pBrand);
        List_AAA.add(bat);
        GUI_Main.addToAccuList(bat);

        return true;
    }

    public static boolean newBrand( final String pString )
    {
        if ( pString.equals("") || pString == null )
            throw new IllegalArgumentException("Brand name must not be empty!");

        for ( Brand b : BRANDS )
        {
            if ( b.toString().equals(pString) )
                return false;
        }

        BRANDS.add(new Brand(pString));
        return true;
    }

    public static boolean removeBrand ( final Brand pBrand )
    {
        if ( pBrand == null )
            throw new IllegalArgumentException("Cannot remove a brand without a name.");

        BRANDS.remove(pBrand);
        return true;
    }

    public static ArrayList<Brand> getBrands()
    {
        return new ArrayList<Brand>(BRANDS);
    }

    public static ArrayList<String> getTypes()
    {
        return new ArrayList<String>(TYPES);
    }
}
