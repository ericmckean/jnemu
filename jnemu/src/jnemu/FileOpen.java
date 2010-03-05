package jnemu;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileOpen 
{
    public static String getFilePath()
    {
        String tmp = "";

        JFileChooser jFile = new JFileChooser();
        jFile.setDialogTitle("Open Rom");

        jFile.setFileFilter(new FileFilter()
        {
             public boolean accept(File f)
             {
                 return f.getName().toLowerCase().endsWith(".nes") || f.isDirectory();
             }

             public String getDescription()
             {
                return "Nes Files";
             }
        });



        if(jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            tmp = jFile.getSelectedFile().getAbsolutePath();
        }
        
        return tmp;
    }
}
