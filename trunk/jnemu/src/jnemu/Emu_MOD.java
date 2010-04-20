package jnemu;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.*;

public class Emu_MOD
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
            Console.print("[JAVA]" + e.toString());
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
       StringBuilder x = new StringBuilder();

       if(tmp.length() < 8)
       {
            switch(tmp.length())
            {
                case 1 : 
                    x.append("0000000");
                    x.append(tmp);
                    break;
                case 2 :
                    x.append("000000");
                    x.append(tmp);
                    break;
                case 3 :
                    x.append("00000");
                    x.append(tmp);
                    break;
                case 4 :
                    x.append("0000");
                    x.append(tmp);
                    break;
                case 5 :
                    x.append("000");
                    x.append(tmp);
                    break;
                case 6 :
                    x.append("00");
                    x.append(tmp);
                    break;
                case 7 :
                    x.append("0");
                    x.append(tmp);
                    break;
                default:
                    x.append(tmp);
                    break;
            }
       }
       return x.toString();
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
            Console.print("[JAVA]" + e.toString());
       }
       return tmp;
   }

}
