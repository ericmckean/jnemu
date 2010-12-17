package EmuConfig;

import java.util.logging.Level;

public class CfgCore
{
    public static void initDefault()
    {
        CfgInfo.setLogFileName("opcode.htm");
        CfgInfo.setLogLevel(Level.INFO);
        CfgInfo.enableLog = false;
        CfgInfo.enableOpcodeLog = false;
        CfgInfo.showConsole = true;
    }
}
