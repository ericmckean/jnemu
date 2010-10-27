package CARTRIDGE;

public class ROM_INFO
{
    public static int MAPPER_NUMBER;
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
        System.out.println("[INFO] File Size : " + (fSize / 1024) + "kb");
        System.out.println("");
        System.out.println("Reading Header...");
        System.out.println("[INFO] Mapper : " + MAPPER_NUMBER);
        System.out.println("[INFO] TV System : " + TVSystem);
        System.out.println("[INFO] 16kb Rom bank : " + NumberOf16KbRomBank);
        System.out.println("[INFO] 8kb VRom bank : " + NumberOf8KbVRomBank);
        System.out.println("[INFO] 8kb Ram bank : " + RamBank_8KB);
        System.out.println("[INFO] Mirroring : " + MIRRORING);
        System.out.println("[INFO] Battery-backed RAM at $6000-$7FFF : " + new Boolean(isBatteryBacked).toString());
        System.out.println("[INFO] 512byte trainer at $7000-$71FF : " + new Boolean(hasTrainer).toString());
        System.out.println("[INFO] 4 Screen VRam Layout : " + new Boolean(is4ScreenVRamLayout).toString());
        System.out.println("");
    }
}
