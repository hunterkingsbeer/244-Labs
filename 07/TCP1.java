import java.io.*;
import java.net.*;
import java.util.*;

public class TCP1 {

    private PrintWriter output;
    private BufferedReader input;
    private Date date = new Date();

    public TCP1(Socket socket) throws Exception {
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void startReceiving() throws Exception {
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println(line);
            output.println("Recieved : " + date.toString());
        }
    }

    public void startSending() throws Exception{
        Scanner stdin = new Scanner(System.in);
        System.err.println("Please enter data here");
        while (stdin.hasNextLine()) {
            output.println(stdin.nextLine());
            System.out.println("Sent : " + date.toString());
            System.out.println(input.readLine());
        }
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            int port = Integer.parseInt(args[0]);
            if (args.length > 1) {
                socket = new Socket(args[1], port);
                System.err.println("Connected to " + args[1] + " on port " + port);
                TCP1 example = new TCP1(socket);
                example.startSending();
            } else {
                ServerSocket serverSocket = new ServerSocket(port);
                System.err.println("Waiting for someone to connect");
                socket = serverSocket.accept();
                System.err.println("Accepted connection on port " + port);
                TCP1 example = new TCP1(socket);
                example.startReceiving();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nUsage: java TCP1 <port> [host]");
        }
    }
}
