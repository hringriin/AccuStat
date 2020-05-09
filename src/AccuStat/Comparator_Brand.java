package AccuStat;

import java.util.Comparator;

public class Comparator_Brand implements Comparator<Brand>
{

    @Override
    public int compare(Brand arg0, Brand arg1)
    {
        return arg0.getName().compareTo(arg1.getName());
    }

}
