package AccuStat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

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

    private static String savePath;

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
    private static GUI_Main GUI = new GUI_Main();

    /**
     * Main method and first to execute.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException
    {
        BATTERIES = new ArrayList<Battery>();
        TYPES = new ArrayList<Type>();
        BRANDS = new ArrayList<Brand>();

        TYPES.add(new Type("AA"));
        TYPES.add(new Type("AAA"));

        BRANDS.add(new Brand("Varta"));

        BATTERIES.add(new Battery("01", TYPES.get(0), BRANDS.get(0)));
        BATTERIES.add(new Battery("02", TYPES.get(0), BRANDS.get(0)));
        BATTERIES.add(new Battery("03", TYPES.get(0), BRANDS.get(0)));
        BATTERIES.add(new Battery("01", TYPES.get(1), BRANDS.get(0)));
        BATTERIES.add(new Battery("02", TYPES.get(1), BRANDS.get(0)));
        BATTERIES.add(new Battery("03", TYPES.get(1), BRANDS.get(0)));

        Main.populateComboBoxes();
        Main.loadMyShit();
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

        Main.throwError(new JFrame(), "HIER HÄTTE ICH NICHT ANKOMMEN SOLLEN!");
        return null;
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
            Main.throwError(new JFrame(), "Brand name must not be empty!");

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
            Main.throwError(new JFrame(), "Type name must not be empty!");

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
            Main.throwError(new JFrame(), "Battery name must not be empty!");

        if ( pType == null )
            Main.throwError(new JFrame(), "Type must not be empty!");

        if ( pBrand == null )
            Main.throwError(new JFrame(), "Brand must not be empty!");

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

    public static void throwError(Object o, String pString)
    {
        if (o instanceof JFrame)
            GUI.throwError((JFrame) o, pString);

        if (o instanceof JDialog)
            GUI.throwError((JDialog) o, pString);
    }

    public static void saveMyShitAss() throws IOException
    {
        savePath = GUI.saveGUI();

        if ( savePath.equals("") || savePath == null )
        {
            Main.throwError(new JFrame(), "No save path defined. Use 'Save As'.");
            return;
        }
        Main.saveMyShit();
    }

    public static void saveMyShit() throws IOException
    {
        String battPath = savePath + "/Batteries";
        String brandPath = savePath + "/Brands";
        String typePath = savePath + "/Types";
        String configPath = savePath + "/Config";

        FileOutputStream fout = new FileOutputStream(battPath);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(BATTERIES);
        oos.close();
        fout.close();

        fout = new FileOutputStream(brandPath);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(BRANDS);
        oos.close();
        fout.close();

        fout = new FileOutputStream(typePath);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(TYPES);
        oos.close();
        fout.close();

        fout = new FileOutputStream(configPath);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(savePath);
        oos.close();
        fout.close();
    }

    @SuppressWarnings("unchecked")
    public static void loadMyShit() throws IOException
    {
        if ( savePath == null || savePath.equals("") )
            savePath = GUI.loadGUI();

        String battPath = savePath + "/Batteries";
        String brandPath = savePath + "/Brands";
        String typePath = savePath + "/Types";
        String configPath = savePath + "/Config";

        System.out.println("EINS");
        FileInputStream fin = new FileInputStream(battPath);
        ObjectInputStream ois = new ObjectInputStream(fin);
        try
        {
            System.out.println("ZWEI");
            BATTERIES = (ArrayList<Battery>) ois.readObject();
        } catch (ClassNotFoundException e)
        {
            System.out.println("DREI");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("VIER");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            System.out.println("FUENF");
            fin.close();
            ois.close();
        }

        fin = new FileInputStream(configPath);
        ois = new ObjectInputStream(fin);
        try
        {
            System.out.println("SECHZEHN");
            savePath = (String) ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("SIEBZEHN");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("ACHTZEHN");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            System.out.println("NEUNZEHN");
            fin.close();
            ois.close();
        }

        System.out.println("SECHS");
        fin = new FileInputStream(brandPath);
        ois = new ObjectInputStream(fin);
        try
        {
            System.out.println("SIEBEN");
            BRANDS = (ArrayList<Brand>) ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("ACHT");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("NEUN");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            System.out.println("ZEHN");
            fin.close();
            ois.close();
        }

        fin = new FileInputStream(typePath);
        ois = new ObjectInputStream(fin);
        try
        {
            System.out.println("ELF");
            TYPES = (ArrayList<Type>) ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("ZWOELF");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("DREIZEHN");
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            System.out.println("VIERZEHN");
            fin.close();
            ois.close();
            Main.populateComboBoxes();
        }
        System.out.println("FUENFZEHN");
    }
}
