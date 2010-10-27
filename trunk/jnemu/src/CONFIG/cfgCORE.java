package CONFIG;

import java.util.logging.Level;

public class cfgCORE
{
    public static void initDefault()
    {
        CFG.setLogFileName("opcode.htm");
        CFG.setLogLevel(Level.INFO);
        CFG.enableLog = false;
        CFG.enableOpcodeLog = false;
        CFG.showConsole = true;
    }
}
