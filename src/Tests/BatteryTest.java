package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import AccuStat.Battery;
import AccuStat.Brand;

class BatteryTest
{

    @Test
    void testBattery()
    {
        Brand b1 = new Brand("Varta");
        Battery aa1 = new Battery("01", "AA", b1);
    }

    @Test
    void testGetName()
    {
        Brand b1 = new Brand("Varta");
        Battery aa1 = new Battery("01", "AA", b1);
        assertEquals("01", aa1.getName());
    }

    @Test
    void testGetVoltage()
    {
        Brand b1 = new Brand("Varta");
        Battery aa1 = new Battery("01", "AA", b1);
        aa1.setVoltage(1.142);
        assertEquals(1.142, aa1.getVoltage());
    }

    @Test
    void testGetVoltages()
    {
        Brand b1 = new Brand("Varta");
        Battery aa1 = new Battery("01", "AA", b1);
        aa1.setVoltage(1.142);
        aa1.setVoltage(1.577);
        aa1.setVoltage(1.000);

        ArrayList<Entry<LocalDateTime, Double>> list = aa1.getVoltages();

        assertEquals(list.get(0).getValue(), 1.142);
        assertEquals(list.get(1).getValue(), 1.577);
        assertEquals(list.get(2).getValue(), 1.000);
    }

    @Test
    void testSetVoltage()
    {
        Brand b1 = new Brand("Varta");
        Battery aa1 = new Battery("01", "AA", b1);

        assertEquals(aa1.setVoltage(0.01), true);
        assertEquals(aa1.setVoltage(0.0), true);
        assertEquals(aa1.setVoltage(2.0), false);
        assertEquals(aa1.setVoltage(1.6), true);
        assertEquals(aa1.setVoltage(1.601), false);
    }

    @Test
    void testGetBrand()
    {
        Brand b1 = new Brand("Varta");
        Battery aa1 = new Battery("01", "AA", b1);

        assertEquals(aa1.getBrand(), b1);
    }

}
