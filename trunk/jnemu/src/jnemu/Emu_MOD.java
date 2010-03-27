package jnemu;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.math.*;

public class Emu_MOD
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
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static String byteToStringHex(byte b)
    {
        // Returns hex String representation of byte b
        char hexDigit[] = {
         '0', '1', '2', '3', '4', '5', '6', '7',
         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
        return new String(array);
   }

   public static String byteToStringInt(byte b)
   {
       int tmp = new Byte(b);
       return Integer.toString(tmp);
   }

   public static String byteToStringBinary(byte b)
   {
       String tmp = Integer.toBinaryString(b);
       return tmp;
   }

}
