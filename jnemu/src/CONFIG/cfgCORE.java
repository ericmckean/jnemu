package CONFIG;

import java.util.logging.Level;

public class cfgCORE
{
    public static void init()
    {
        LOG_CFG.setLogFileName("opcode.htm");
        LOG_CFG.setLogLevel(Level.INFO);
        LOG_CFG.enableLog = false;
        LOG_CFG.enableOpcodeLog = true;
    }
}
