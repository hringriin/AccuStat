package AccuStat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JFrame;

import GUI.GUI_Main;

/**
 * AccuStat
 *
 * This class contains all the important lists of objects
 *
 * @author Joschka Köster
 * @version 0.3.1
 * @since 0.1a
 */
public class Main {
    /**
     * The Version number
     */
    private static String versionNumber = "v0.3.1";

    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
        return new ArrayList<Brand>(BRANDS);
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
        Collections.sort(BATTERIES, new Comparator_Battery());
        Collections.sort(BRANDS, new Comparator_Brand());
        Collections.sort(TYPES, new Comparator_Type());
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

        if (savePath == null || savePath.equals("") )
        {
            Main.throwError(new JFrame(), "No save path defined. Use 'Save As'.");
            return;
        }
        Main.saveMyShit();
    }

    public static void saveMyShit() throws IOException
    {
        if (savePath == null || savePath.equals("") )
            return;

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
        savePath = GUI.loadGUI();

        if (savePath == null || savePath.equals("") )
            return;

        String battPath = savePath + "/Batteries";
        String brandPath = savePath + "/Brands";
        String typePath = savePath + "/Types";
        String configPath = savePath + "/Config";

        FileInputStream fin = new FileInputStream(battPath);
        ObjectInputStream ois = new ObjectInputStream(fin);
        try
        {
            BATTERIES = (ArrayList<Battery>) ois.readObject();
        } catch (ClassNotFoundException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            fin.close();
            ois.close();
        }

        fin = new FileInputStream(configPath);
        ois = new ObjectInputStream(fin);
        try
        {
            savePath = (String) ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            fin.close();
            ois.close();
        }

        fin = new FileInputStream(brandPath);
        ois = new ObjectInputStream(fin);
        try
        {
            BRANDS = (ArrayList<Brand>) ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            fin.close();
            ois.close();
        }

        fin = new FileInputStream(typePath);
        ois = new ObjectInputStream(fin);
        try
        {
            TYPES = (ArrayList<Type>) ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Main.throwError(new JFrame(), e.toString());
            e.printStackTrace();
        }
        finally
        {
            fin.close();
            ois.close();
            Main.populateComboBoxes();
        }
    }

    public static void allNew()
    {
        BATTERIES = new ArrayList<Battery>();
        TYPES = new ArrayList<Type>();
        BRANDS = new ArrayList<Brand>();
        Main.populateComboBoxes();
    }
}
