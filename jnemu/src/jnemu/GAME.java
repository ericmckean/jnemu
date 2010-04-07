package jnemu;

public class GAME
{
    public static String MAPPER;
    public static String TVSystem;
    public static int RomBank_16KB;
    public static int VRomBank_8KB;
    public static int RamBank_8KB;
    public static String MIRRORING;
    public static boolean isBatteryBacked;
    public static boolean Trainer_512KB;
    public static boolean VRamLayout_4Screen;
    public static byte[][] RomBank_Content_16KB;
    public static byte[][] VRomBank_Content_8KB;

    public static void showInfo()
    {
        Console.print("[INFO] Mapper : " + MAPPER);
        Console.print("[INFO] TV System : " + TVSystem);
    }
}
