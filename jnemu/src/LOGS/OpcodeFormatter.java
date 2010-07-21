package LOGS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

//This custom formatter formats parts of a log record to a single line
class OpcodeFormatter extends Formatter
{
    // This method is called for every log records
    public String format(LogRecord rec)
    {
        StringBuffer buf = new StringBuffer(1000);
        buf.append(rec.getMessage());
        return buf.toString();
    }

    private String calcDate(long millisecs)
    {
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    @Override
    public String getHead(Handler h)
    {
        StringBuilder tmp = new StringBuilder(10);

        tmp.append("<html><head></head><body>");
        tmp.append("<table border='0'><tr>");
        tmp.append("<td width='100'>");
        tmp.append("<b>ADDR</b>");
        tmp.append("</td>");
        tmp.append("<td width='100'>");
        tmp.append("<b>CODE</b>");
        tmp.append("</td>");
        tmp.append("<td width='400'>");
        tmp.append("<b>REGISTER</b>");
        tmp.append("</td></tr>");
        return tmp.toString();
    }

    @Override
    public String getTail(Handler h)
    {
        return "</table></body></html>";
    }
}
