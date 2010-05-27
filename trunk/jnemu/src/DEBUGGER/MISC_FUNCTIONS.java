package DEBUGGER;

public class MISC_FUNCTIONS
{
    public static String forceTo8Bit(int i)
    {
        StringBuilder x = new StringBuilder(2);
        switch(Integer.toHexString(i).length())
        {
            case 1 :
                x.append("0");
                x.append(Integer.toHexString(i));
                break;
            case 2 :
                x.append(Integer.toHexString(i));
                break;
        }
        return x.toString().toUpperCase();
    }

    public static String forceTo16Bit(int i)
    {
        StringBuilder x = new StringBuilder(2);
        switch(Integer.toHexString(i).length())
        {
            case 1 :
                x.append("000");
                x.append(Integer.toHexString(i));
                break;
            case 2 :
                x.append("00");
                x.append(Integer.toHexString(i));
                break;
            case 3 :
                x.append("0");
                x.append(Integer.toHexString(i));
                break;
            default :
                x.append(Integer.toHexString(i));
                break;
        }
        return x.toString().toUpperCase();
    }
}
