import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.System;
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
        //Ensure proper test points are entered
        if (numOfRequests == 1 || numOfRequests == 5 || numOfRequests == 10 ||
                numOfRequests == 15 || numOfRequests == 20 || numOfRequests == 25) {
            Thread[] threads = new Thread[numOfRequests]; //array for threads
            long[] threadTimes = new long[threads.length]; //array for storing thread times
            for(int i = 0; i < numOfRequests; i++) {
                final int threadIndex = i;  // preserves this thread's array index
                //lambda expression
                threads[i] = new Thread(() -> {
                    //Connect to server
                    try {
                        Socket socket = new Socket(ipAddress, port);

                        //Send request
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        long startTime = System.nanoTime(); //start timer for individual thread request
                        out.println(requestSelection);  //send request

                        //Receive response from server
                        BufferedReader in = new BufferedReader(new InputStreamReader
                                (socket.getInputStream()));
                        String response;
                        while((response = in.readLine()) != null) {
                            System.out.println(response);
                        } // end while loop: reads each line of server response
                        long endTime = System.nanoTime(); // end timer for response from server
                        threadTimes[threadIndex] = endTime - startTime; // store elapsed time in array index
                    } catch (IOException e) {
                        System.out.println("Connection failed: " + e.getMessage());
                    }
                }); // end lambda expression
                threads[i].start();
            }// end for loop
            for (int i = 0; i < numOfRequests; i++) {
                threads[i].join();
            }// end for loop

            // iterate through threadTimes for sum of thread times
            long totalTime = 0;
            for(int i = 0; i < threadTimes.length; i++) {
                totalTime += threadTimes[i];
            } // end for loop
            System.out.println();
            //Individual turnaround times
            for (int i = 0; i < threadTimes.length; i++) {
                System.out.println("Thread " + (i + 1) + ": " + (double) threadTimes[i]/1000000.0 + " ms");
            }
            //average of times converted from nano to milliseconds
            double averageTime = (totalTime / (double) threadTimes.length) / 1000000.0;

            System.out.println("Number of Requests initiated: " + numOfRequests);
            System.out.println("Total Elapsed Time: " + (double) totalTime/1000000.0 + " ms");
            System.out.println("Average Time: " + averageTime + " ms");
            System.out.println();
        }
        else {
            System.out.println("Invalid Selection");
            System.out.println("Select: 1, 5, 10, 15, 20, or 25");
        } // end if/else

    }//end requestHandler

}//end ClientCommand class

