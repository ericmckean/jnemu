package CONFIG;

import java.util.logging.Level;

public class CFG
{
    private static String LogFileName;
    private static Level LogLevel;
    public static boolean enableLog;
    public static boolean enableOpcodeLog;
    public static boolean showConsole;

    public static void setLogFileName(String Name)
    {
        LogFileName = Name;
    }

    public static String getLogFileName()
    {
        return LogFileName;
    }

    public static void setLogLevel(Level lvl)
    {
        LogLevel = lvl;
    }

    public static Level getLogLevel()
    {
        return LogLevel;
    }
    
    public static String getDefaultConsoleTitle()
    {
        return "JNemu";
    }
}
