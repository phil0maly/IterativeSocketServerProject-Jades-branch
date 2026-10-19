import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner in = new Scanner(System.in);

        // Prompt for IP Address
        System.out.println("Please Enter IP Address: ");
        String ipAddress = in.nextLine();
        /*
        Check for proper IP address format via Regular Expression (REGEX)
        --While loop Re-prompt if incorrect
        */
        boolean valid = ipAddress.matches("((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}" +
                "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)");
        while(!valid){
            System.out.println("Enter proper IP Address. (Format: xxx.xxx.xxx.xxx)");
            ipAddress = in.nextLine();
            valid = ipAddress.matches("((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}" +
                    "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)");
        } // end while loop

        // Prompt for Port Number
        System.out.print("Please Enter Port: ");
        Integer port = in.nextInt();
        //Socket socket = new Socket(ipAddress, port);
        //PrintWriter request = new PrintWriter(socket.getOutputStream(), true);
        //BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println();

        int requestSelection = 0;
        while(requestSelection != 7) {
            // Prompt Server for Corresponding Request(s)
            System.out.println("Please Enter Number Corresponding to Desired Command");
            System.out.println("1 - Date and Time");
            System.out.println("2 - Uptime");
            System.out.println("3 - Memory Use");
            System.out.println("4 - Netstat");
            System.out.println("5 - Current Users");
            System.out.println("6 - Running Process");
            System.out.println("7 - Exit Program");
            System.out.println();

            requestSelection = in.nextInt();
            ClientCommand req = new ClientCommand(requestSelection);
            ClientCommand returned;

                int numOfRequests;
                switch (requestSelection) {

                    // Date and Time
                    case 1:
                        System.out.println("Please enter Number of Requests");
                        System.out.println("Options: 1, 5, 10, 15, 20, 25");
                        numOfRequests = in.nextInt();
                        req.requestHandler(req.dateTime(), numOfRequests, ipAddress, port);
                        System.out.println("Completed Date and Time Request");
                        break;

                    // Uptime
                    case 2:
                        System.out.println("Please enter Number of Requests");
                        System.out.println("Options: 1, 5, 10, 15, 20, 25");
                        numOfRequests = in.nextInt();
                        req.requestHandler(req.upTime(), numOfRequests, ipAddress, port);
                        System.out.println("Completed Uptime Request");
                        break;

                    // Memory Use
                    case 3:
                        System.out.println("Please enter Number of Requests");
                        System.out.println("Options: 1, 5, 10, 15, 20, 25");
                        numOfRequests = in.nextInt();
                        req.requestHandler(req.memUse(), numOfRequests, ipAddress, port);
                        System.out.println("Completed Memory Use Request");
                        break;

                    // Netstat
                    case 4:
                        System.out.println("Please enter Number of Requests");
                        System.out.println("Options: 1, 5, 10, 15, 20, 25");
                        numOfRequests = in.nextInt();
                        req.requestHandler(req.netStat(), numOfRequests, ipAddress, port);
                        System.out.println("Completed Netstat Request");
                        break;

                    // Current Users
                    case 5:
                        System.out.println("Please enter Number of Requests");
                        System.out.println("Options: 1, 5, 10, 15, 20, 25");
                        numOfRequests = in.nextInt();
                        req.requestHandler(req.currentUsers(), numOfRequests, ipAddress, port);
                        System.out.println("Completed Current Users Request");
                        break;

                    // Running Processes
                    case 6:
                        System.out.println("Please enter Number of Requests");
                        System.out.println("Options: 1, 5, 10, 15, 20, 25");
                        numOfRequests = in.nextInt();
                        req.requestHandler(req.runningProcesses(), numOfRequests, ipAddress, port);
                        System.out.println("Completed Running Processes Request");
                        break;

                    // Exit Program
                    case 7:
                        System.out.println("Exiting Program");
                        req.exitProgram();
                        Thread.sleep(2000);
                        break;
                } // end switch statement
        } // end while loop
    }
}
