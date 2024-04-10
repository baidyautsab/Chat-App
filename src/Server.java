import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader br; // for reading
    PrintWriter out; // for writing

    public Server(){
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("Server is ready to connect");
            System.out.println("Waiting...");
            socket = serverSocket.accept();
            System.out.println("Connection done");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startReading(){ // accepting data
        // creating a thread object of Runnable interface using lambda expression
        Runnable r1 = ()->{
            while (true){ // for now the loop will be executed for infinite time
                try {
                    String clientMsg = br.readLine();
                    System.out.println("Client: " + clientMsg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(r1).start(); // executing the thread
    }

    public void startWriting(){ // sending data
        // creating a thread object of Runnable interface using lambda expression
        Runnable r2 = ()->{
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String clientMsg = consoleReader.readLine(); // Read from console
                    out.println(clientMsg); // Send the message to server
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(r2).start(); // executing the thread
    }

    public static void main(String[] args) {
        System.out.println("Server is started!");
        Server server = new Server();
        server.startReading();
        server.startWriting();
    }
}
