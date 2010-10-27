package LOGS;

import java.util.logging.*;
import CONFIG.CFG;

public class LOGGER
{
    private static FileHandler opcodeHtml;
    private static Logger opcodeLogger = Logger.getLogger("");
    private static Formatter opFor;

    public static void init()
    {
        try
        {
            opcodeHtml = new FileHandler(CFG.getLogFileName());
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
        opcodeLogger.log(CFG.getLogLevel(), logStr);
    }
}
