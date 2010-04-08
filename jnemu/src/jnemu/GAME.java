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
    public static boolean hasTrainer;
    public static boolean is4ScreenVRamLayout;
    public static byte[][] RomBank_Content_16KB; // 16kb Rom bank [Upper bound of Rom byte array][RomBank_16KB]
    public static byte[][] VRomBank_Content_8KB;
    public static byte[] Trainer;

    public static void showInfo()
    {
        Console.print("[INFO] Mapper : " + MAPPER);
        Console.print("[INFO] TV System : " + TVSystem);
        Console.print("[INFO] 16kb Rom bank : " + RomBank_16KB);
        Console.print("[INFO] 8kb VRom bank : " + VRomBank_8KB);
        Console.print("[INFO] 8kb Ram bank : " + RamBank_8KB);
        Console.print("[INFO] Mirroring : " + MIRRORING);
        Console.print("[INFO] Battery-backed RAM at 0x6000-0x7FFF : " + new Boolean(isBatteryBacked).toString());
        Console.print("[INFO] 512byte trainer at 0x7000-0x71FF : " + new Boolean(hasTrainer).toString());
        Console.print("[INFO] 4 Screen VRam Layout : " + new Boolean(is4ScreenVRamLayout).toString());

    }
}
