package jnemu;

public class GAME
{
    public static int MAPPER;
    public static String TVSystem;
    public static int NumberOf16KbRomBank;
    public static int NumberOf8KbVRomBank;
    public static int RamBank_8KB;
    public static String MIRRORING;
    public static boolean isBatteryBacked;
    public static boolean hasTrainer;
    public static boolean is4ScreenVRamLayout;
    public static byte[][] RomBank_16KB; // 16kb Rom bank [Upper bound of Rom byte array][NumberOf16KbRomBank]
    public static byte[][] VRomBank_8KB; // 8kb VRom Bank [Upper bound of VRom byte array][NumberOf8KbVRomBank]
    public static byte[] Trainer;
    public static long fSize;

    public static void showInfo()
    {
        Console.print("[INFO] File Size : " + fSize);
        Console.print("[INFO] Mapper : " + MAPPER);
        Console.print("[INFO] TV System : " + TVSystem);
        Console.print("[INFO] 16kb Rom bank : " + NumberOf16KbRomBank);
        Console.print("[INFO] 8kb VRom bank : " + NumberOf8KbVRomBank);
        Console.print("[INFO] 8kb Ram bank : " + RamBank_8KB);
        Console.print("[INFO] Mirroring : " + MIRRORING);
        Console.print("[INFO] Battery-backed RAM at 0x6000-0x7FFF : " + new Boolean(isBatteryBacked).toString());
        Console.print("[INFO] 512byte trainer at 0x7000-0x71FF : " + new Boolean(hasTrainer).toString());
        Console.print("[INFO] 4 Screen VRam Layout : " + new Boolean(is4ScreenVRamLayout).toString());
    }
}
