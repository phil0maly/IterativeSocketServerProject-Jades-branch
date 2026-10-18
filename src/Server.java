import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws InterruptedException, IOException {

        // 1. Create a socket and bind to a port number
        int port = 8383;
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {

            // 2. Listen for a connection
            Socket socket = serverSocket.accept();

            // 3. Read data from client via InputStream obtained from the client socket
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // 3a. perform system command

            // 4. Send data to the client via the client's socket OutputStream
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            //writer.println(results);
        } // end while loop

        // 5. Close the connection with the client
        //serverSocket.close();

    } // end main method
} // end Server class
