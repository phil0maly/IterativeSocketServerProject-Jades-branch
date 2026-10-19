import java.io.IOException;
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

    protected void requestHandler(Client req, int numOfRequests,
                                  String ipAddress, int port) {
        if (numOfRequests == 1 || numOfRequests == 5 || numOfRequests == 10 ||
                numOfRequests == 15 || numOfRequests == 20 || numOfRequests == 25) {
            for(int i = 0; i < numOfRequests; i++) {
                Thread thread = new Thread(() -> {
                    //Connect to server
                    try {
                        Socket socket = new Socket(String ipAddress, int port);
                    } catch (IOException e) {
                        // what should I put here?
                    }
                    //Send request
                    //Recieve response
                });
                thread.start();
            }// end for loop
        } else {
            System.out.println("Invalid Selection");
            System.out.println("Select: 1, 5, 10, 15, 20, or 25");
            } // end if/else

    }//end requestHandler

}//end ClientCommand class

