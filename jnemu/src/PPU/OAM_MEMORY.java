package PPU;

import MISC.CONVERTER;
import java.text.DecimalFormat;
import jnemu.Console;

public class OAM_MEMORY
{
    private static int[] OAM_MAP;

    public static void init()
    {
        OAM_MAP = new int[0x101];
    }

    public static int Read(int address)
    {
        return OAM_MAP[address];
    }

    public static void Write(int address, int Value)
    {
        OAM_MAP[address] = Value;
    }

    public static String getMemContent(int start, int end)
    {
        int ctr, ctr2, tmp;
        int BASE = 16;
        String space = " ";
        String space2 = " : ";
        String space3 = "   ";
        DecimalFormat dfUpper = new DecimalFormat("00");
        StringBuilder x = new StringBuilder();
        StringBuilder UTF8 = new StringBuilder(50);

        try
        {
            x.append("        ");
            for(ctr=0;ctr<BASE;ctr++)
            {
                x.append(dfUpper.format(ctr));
                x.append(space);
            }
            x.append("\n\n");
            x.append("$" + CONVERTER.intTo16BitStringHex(start));
            x.append(space2);

            ctr2 = 0;

            for(ctr=start; ctr<=end; ctr++)
            {
                ctr2 += 1;
                tmp = OAM_MEMORY.Read(ctr);
                if(ctr2 != BASE)
                {
                    if(tmp != 0)
                    {
                        x.append(CONVERTER.intTo8BitStringHex(tmp));
                        x.append(space);
                        UTF8.append(CONVERTER.intToChar(tmp));
                        UTF8.append(space);
                    }
                    else
                    {
                        x.append("00");
                        x.append(space);
                        UTF8.append(".");
                        UTF8.append(space);
                    }
                }
                else
                {
                    if(tmp != 0)
                    {
                        x.append(CONVERTER.intTo8BitStringHex(tmp));
                        UTF8.append(CONVERTER.intToChar(tmp));
                        UTF8.append(space);
                        x.append(space3);
                        x.append(UTF8.toString());
                        UTF8.delete(0, UTF8.length());
                        x.append("\n");
                        x.append("$" + CONVERTER.intTo16BitStringHex(ctr));
                        x.append(space2);
                    }
                    else
                    {
                        x.append("00");
                        UTF8.append(".");
                        UTF8.append(space);
                        x.append(space3);
                        x.append(UTF8.toString());
                        UTF8.delete(0, UTF8.length());
                        x.append("\n");
                        x.append("$" + CONVERTER.intTo16BitStringHex(ctr));
                        x.append(space2);
                    }
                    ctr2 = 0;
                }
            }
        }
        catch(Exception e)
        {
            Console.print("[ERROR] Load the Rom first.");
        }
        return x.toString();
    }
}
