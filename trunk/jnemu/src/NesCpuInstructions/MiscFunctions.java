package NesCpuInstructions;

public class MiscFunctions
{
    public static String intTo8BitHexString(int i)
    {
        StringBuilder sb = new StringBuilder(2);

        switch(Integer.toHexString(i).length())
        {
            case 1 :
                sb.append("0");
                sb.append(Integer.toHexString(i));
                break;
            default :
                sb.append(Integer.toHexString(i));
                break;
        }
        return sb.toString();
    }
}
