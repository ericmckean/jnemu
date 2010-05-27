package MISC;

public class CONVERTER
{
   public static String byteTo8BitStringHex(byte b)
   {
       StringBuilder tmp = new StringBuilder();
       String x;

       x = Integer.toHexString(b & 0xff);
       if(x.length() < 2)
       {
           tmp.append("0");
           tmp.append(x);
       }
       else
       {
           tmp.append(x);
       }
       return tmp.toString();
   }

   public static String byteTo16BitStringHex(byte b)
   {
       StringBuilder tmp = new StringBuilder();
       String x;

       x = Integer.toHexString(b & 0xffff);
       switch(x.length())
       {
           case 1 :
               tmp.append("000");
               tmp.append(x);
               break;
           case 2 :
               tmp.append("00");
               tmp.append(x);
               break;
           case 3 :
               tmp.append("0");
               tmp.append(x);
               break;
           default :
               tmp.append(x);
               break;
       }
       return tmp.toString();
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

   public static String intToStringBinary(int i)
   {
       String tmp;
       StringBuilder s = new StringBuilder();

       tmp = Integer.toBinaryString(i);
       switch(tmp.length())
       {
           case 7 :
               s.append("0");
               s.append(tmp);
               break;
           case 6 :
               s.append("00");
               s.append(tmp);
               break;
           case 5 :
               s.append("000");
               s.append(tmp);
               break;
           case 4 :
               s.append("0000");
               s.append(tmp);
               break;
           case 3 :
               s.append("00000");
               s.append(tmp);
               break;
           case 2 :
               s.append("000000");
               s.append(tmp);
               break;
           case 1 :
               s.append("0000000");
               s.append(tmp);
               break;
           default :
               s.append(tmp);
               break;
       }

       return s.toString();
   }

   public static String stringBinaryToStringHex(String s)
   {
        int i;

        i = Integer.parseInt(s, 2);
        return Integer.toHexString(i);
   }

}
