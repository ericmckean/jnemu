package EmuLog;

import java.util.logging.*;
import EmuConfig.CfgInfo;

public class EmuLogger
{
    private static FileHandler opcodeHtml;
    private static final Logger opcodeLogger = Logger.getLogger("");
    private static Formatter opFor;

    public static void init()
    {
        try
        {
            opcodeHtml = new FileHandler(CfgInfo.getLogFileName());
            opcodeLogger.addHandler(opcodeHtml);
            opFor = new OpcodeFormatter();
            opcodeHtml.setFormatter(opFor);
        }
        catch(Exception ex)
        {
            //Do nothing as of now...
        }
    }

    public static void write(String logStr)
    {
        opcodeLogger.log(CfgInfo.getLogLevel(), logStr);
    }
}
