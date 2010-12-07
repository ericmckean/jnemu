package CARTRIDGE;

public class RomInfo
{
    public static int mapperNumber;
    public static String tvSystem;
    public static int numberOf16KbRomBank;
    public static int numberOf8KbVRomBank;
    public static int ramBank_8KB;
    public static String mirroringInfo;
    public static boolean isBatteryBacked;
    public static boolean hasTrainer;
    public static boolean is4ScreenVRamLayout;
    public static byte[][] romBank_16KB; // 16kb Rom bank [Upper bound of Rom byte array][NumberOf16KbRomBank]
    public static byte[][] vRomBank_8KB; // 8kb VRom Bank [Upper bound of VRom byte array][NumberOf8KbVRomBank]
    public static byte[] trainerInfo;
    public static long fSize;

    public static void showInfo()
    {
        System.out.println("[INFO] File Size : " + (fSize / 1024) + "kb");
        System.out.println("");
        System.out.println("Reading Header...");
        System.out.println("[INFO] Mapper : " + mapperNumber);
        System.out.println("[INFO] TV System : " + tvSystem);
        System.out.println("[INFO] 16kb Rom bank : " + numberOf16KbRomBank);
        System.out.println("[INFO] 8kb VRom bank : " + numberOf8KbVRomBank);
        System.out.println("[INFO] 8kb Ram bank : " + ramBank_8KB);
        System.out.println("[INFO] Mirroring : " + mirroringInfo);
        System.out.println("[INFO] Battery-backed RAM at $6000-$7FFF : " + new Boolean(isBatteryBacked).toString());
        System.out.println("[INFO] 512byte trainer at $7000-$71FF : " + new Boolean(hasTrainer).toString());
        System.out.println("[INFO] 4 Screen VRam Layout : " + new Boolean(is4ScreenVRamLayout).toString());
        System.out.println("");
    }
}
