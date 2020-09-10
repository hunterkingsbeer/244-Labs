import java.io.*;
import java.net.*;
import java.util.*;

public class TCP2 {

    private PrintWriter output;
    private BufferedReader input;
    private Date date = new Date();

    public TCP2(Socket socket) throws Exception {
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }






    public void startReceiving() throws Exception {
        String line;
        Scanner stdin = new Scanner(System.in);
        while ((line = input.readLine()) != null) {
            System.out.println("Received : " + line);
            if (stdin.hasNextLine()){

                output.println(stdin.nextLine());
            }
        }
    }

    public void startSending() throws Exception{
        Scanner stdin = new Scanner(System.in);
        String line;
        System.err.println("Enter data here");
        while (stdin.hasNextLine()) {

            output.println(stdin.nextLine());
            line = input.readLine();
            System.out.println("Received : " + line);
        }
    }





    public static void main(String[] args) {
        Socket socket = null;
        try {
            int port = Integer.parseInt(args[0]);
            if (args.length > 1) {
                socket = new Socket(args[1], port);
                System.err.println("Connected to " + args[1] + " on port " + port);
                TCP2 example = new TCP2(socket);
                example.startSending();
            } else {
                ServerSocket serverSocket = new ServerSocket(port);
                System.err.println("Waiting for someone to connect");
                socket = serverSocket.accept();
                System.err.println("Accepted connection on port " + port);
                TCP2 example = new TCP2(socket);
                example.startReceiving();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nUsage: java TCP2 <port> [host]");
        }
    }
}
