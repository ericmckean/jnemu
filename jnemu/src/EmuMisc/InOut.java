package EmuMisc;

import jnemu.Main;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.*;

public class InOut
{
    public static String getFilePath()
    {
        String tmp = "";

        try
        {
            JFileChooser jFile = new JFileChooser(new java.io.File(".").getCanonicalFile());
            jFile.setDialogTitle("Open Rom");
            //jFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            jFile.setFileFilter(new FileFilter()
            {
                 public boolean accept(File f)
                 {
                     return f.getName().toLowerCase().endsWith(".nes") || f.isDirectory();
                 }

                 public String getDescription()
                 {
                    return "Nes Files (*.nes)";
                 }
            });

            if(jFile.showOpenDialog(Main.win) == JFileChooser.APPROVE_OPTION)
            {
                tmp = jFile.getSelectedFile().getAbsolutePath();
            }
        }
        catch(Exception e)
        {
            System.out.println("[getFilePath]" + e.toString());
        }

        return tmp;
    }

    public static byte[] getBytesFromFile(File file) throws IOException
    {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE)
        {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0)
        {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length)
        {
            throw new IOException("[getBytesFromFile] Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    

   public static String getCharFromString(int pos, String str)
   {
       String tmp = "";
       try
       {
            tmp = str.substring(pos - 1, pos);
       }
       catch (Exception e)
       {
            System.out.println("[getCharFromString]" + e.toString());
       }
       return tmp;
   }

}
