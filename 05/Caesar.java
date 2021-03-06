import java.io.*;

/** Caesar Cipher Encryption.
 * @author Hunter Kingsbeer. */
public class Caesar{

    public static void main(String[] args){

        InputStream in = System.in;
        OutputStream out = System.out;
        int shift = Integer.parseInt(args[0]);
        int input;

        try{

            while((input = in.read()) != -1){
                input += shift;
                if(input > 255) input = input % 255;

                out.write(input);
                out.flush();
            }
            out.close();


        } catch (IOException e){
            System.err.println("EXECEPTION : " + e);
        }


    }

}
