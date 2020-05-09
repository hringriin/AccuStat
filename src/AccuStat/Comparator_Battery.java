package AccuStat;

import java.util.Comparator;

public class Comparator_Battery implements Comparator<Battery>
{

    @Override
    public int compare(Battery arg0, Battery arg1)
    {
        return arg0.toString().compareTo(arg1.toString());
    }

}
