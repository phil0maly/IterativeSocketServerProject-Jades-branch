import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.lang.ProcessBuilder;
import java.lang.Thread;
import java.net.Socket;

public class ClientCommand {
    Integer request;

    // Constructor
    public ClientCommand(Integer request) {
        this.request = request;
    }

    // Process builder Factory Method
    protected ProcessBuilder sysCommand (String... command) {
        return new ProcessBuilder(command);
    }

    protected ProcessBuilder dateTime() {
        return sysCommand("date");
    }

    protected ProcessBuilder upTime() {
        return sysCommand("uptime");
    }

    protected ProcessBuilder memUse() {
        return sysCommand("free");
    }

    protected ProcessBuilder netStat() {
        return sysCommand("netstat");
    }

    protected ProcessBuilder currentUsers() {
        return sysCommand("who");
    }

    protected ProcessBuilder runningProcesses() {
        return sysCommand("ps", "-ef");
    }

    protected ProcessBuilder exitProgram() {
        return sysCommand("exit");
    }

    protected void requestHandler(int requestSelection, int numOfRequests,
                                  String ipAddress, int port) throws InterruptedException {
        if (numOfRequests == 1 || numOfRequests == 5 || numOfRequests == 10 ||
                numOfRequests == 15 || numOfRequests == 20 || numOfRequests == 25) {
            Thread[] threads = new Thread[numOfRequests];
            for(int i = 0; i < numOfRequests; i++) {
                threads[i] = new Thread(() -> {
                    //Connect to server
                    try {
                        Socket socket = new Socket(ipAddress, port);

                        //Send request
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(requestSelection);
                        //Receive response
                        BufferedReader in = new BufferedReader(new InputStreamReader
                                (socket.getInputStream()));
                        String response;
                        while((response = in.readLine()) != null) {
                            System.out.println(response);
                        } // end while loop: reads each line of server response
                    } catch (IOException e) {
                        System.out.println("Coonnection failed: " + e.getMessage());
                    }
                });
                threads[i].start();
            }// end for loop
            for (int i = 0; i < numOfRequests; i++) {
                threads[i].join();
            }
            System.out.println("Number of Requests initiated: " + numOfRequests);
        }
        else {
            System.out.println("Invalid Selection");
            System.out.println("Select: 1, 5, 10, 15, 20, or 25");
        } // end if/else

    }//end requestHandler

}//end ClientCommand class

