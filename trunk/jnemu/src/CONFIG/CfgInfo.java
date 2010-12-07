package CONFIG;

import java.util.logging.Level;

public class CfgInfo
{
    private static String logFileName;
    private static Level logLevel;
    public static boolean enableLog;
    public static boolean enableOpcodeLog;
    public static boolean showConsole;

    public static void setLogFileName(String Name)
    {
        logFileName = Name;
    }

    public static String getLogFileName()
    {
        return logFileName;
    }

    public static void setLogLevel(Level lvl)
    {
        logLevel = lvl;
    }

    public static Level getLogLevel()
    {
        return logLevel;
    }
    
    public static String getDefaultConsoleTitle()
    {
        return "JNemu";
    }
}
