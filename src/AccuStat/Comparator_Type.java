package AccuStat;

import java.util.Comparator;

public class Comparator_Type implements Comparator<Type>
{
    @Override
    public int compare(Type arg0, Type arg1)
    {
        return arg0.getName().compareTo(arg1.getName());
    }

}
