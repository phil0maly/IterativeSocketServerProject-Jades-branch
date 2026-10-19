import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws InterruptedException, IOException {

        // 1. Create a socket and bind to a port number
        String ipAddress = "192.168.1.46";
        int port = 2525;
        ServerSocket serverSocket = new ServerSocket(port, 25);
        while(true) {

            // 2. Listen for a connection
            Socket socket = serverSocket.accept();

            // 3. Read the request
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String request = reader.readLine();

            // 4. perform system command
            ClientCommand command = new ClientCommand(Integer.parseInt(request));  //convert String req to number
            ProcessBuilder process;
            switch(Integer.parseInt(request)) {
                case 1:
                    process = command.dateTime();
                    break;
                case 2:
                    process = command.upTime();
                    break;
                case 3:
                    process = command.memUse();
                    break;
                case 4:
                    process = command.netStat();
                    break;
                case 5:
                    process = command.currentUsers();
                    break;
                case 6:
                    process = command.runningProcesses();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Command");
            } // end Switch statement
            Process runningProcess = process.start();

            String response;
            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader(runningProcess.getInputStream())
            );
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            while ((response = responseReader.readLine()) != null){
                writer.println(response);
            }
            socket.close();

        } // end while loop
    } // end main method
} // end Server class
