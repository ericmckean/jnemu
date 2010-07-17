package CONFIG;

import java.util.logging.Level;

public class LOG_CFG
{
    private static String LogFileName;
    private static Level LogLevel;
    public static boolean enableLog;

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
}
