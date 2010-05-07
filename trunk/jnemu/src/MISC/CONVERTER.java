package MISC;

public class CONVERTER
{
   public static String byteTo8BitStringHex(byte b)
   {
        return Integer.toHexString(b & 0xff);
   }

   public static String byteTo16BitStringHex(byte b)
   {
        return Integer.toHexString(b & 0xffff);
   }

   public static int stringHexToInt(String str)
   {
       return Integer.parseInt(str,16);
   }

   public static String byteToStringInt(byte b)
   {
       int tmp = new Byte(b);
       return Integer.toString(tmp);
   }

   public static String byteTo8BitStringBinary(byte b)
   {
       String tmp = Integer.toBinaryString(b);
       StringBuilder x = new StringBuilder();

       if(tmp.length() < 8)
       {
            switch(tmp.length())
            {
                case 0 :
                    x.append("00000000");
                    break;
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

}
