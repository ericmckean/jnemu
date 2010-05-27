package INSTRUCTIONS;

public class MEM_PAGER
{
    public static int getPage(int address)
    {
        return (address >> 8);
    }
}
