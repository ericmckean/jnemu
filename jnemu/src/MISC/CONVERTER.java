package MISC;

public class CONVERTER
{
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

   public static String byteTo16BitStringHex(byte b)
   {
        return Integer.toHexString(b & 0xffff);
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
