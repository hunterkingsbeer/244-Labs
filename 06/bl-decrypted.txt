import java.io.*;
import java.util.*;

/** Bit level Cipher Encryption.
 * @author Hunter Kingsbeer. */

public class BitLevel{

    public static void main(String[] args){

        String key;
        byte[] bitKey;

        System.out.println("Enter key : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        bitKey = key.getBytes();

        try{
            String in = args[0];
            String out = args[1];
            FileInputStream input = new FileInputStream(in);
            FileOutputStream output = new FileOutputStream(out);
            int count = 0;
            int byt;

            while((byt = input.read()) != -1){
                if(count >= key.length()) count = 0;

                output.write(bitKey[count]^byt);
                count++;
                output.flush();
            }
            output.close();
        } catch (IOException e){
            System.err.println("EXCEPTION : " + e);
        }
    }

}