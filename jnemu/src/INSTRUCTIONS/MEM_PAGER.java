package INSTRUCTIONS;

public class MEM_PAGER
{
    public static int getPage(int i)
    {
        return (i >> 0x80);
    }
}
