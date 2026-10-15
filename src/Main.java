import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please Enter Port: ");
        Integer port = in.nextInt();
        System.out.println();
        System.out.println("Please Enter Number Corresponding to Desired Command");
        System.out.println("1 - Date and Time");
        System.out.println("2 - Uptime");
        System.out.println("3 - Memory Use");
        System.out.println("4 - Netstat");
        System.out.println("5 - Current Users");
        System.out.println("6 - Running Process");
        System.out.println("7 - Exit Program");

        int requestSelection = in.nextInt();
        ClientCommand request = new ClientCommand(requestSelection);
        ClientCommand returned;

        System.out.println("Please Enter Number of Requests");

        switch (requestSelection) {
            case 1:
               System.out.println(request.dateTime());
               break;
            case 2:
                System.out.println(request.upTime());
                break;
            case 3:
                System.out.println(request.memUse());
                break;
            case 4:
                System.out.println(request.netStat());
                break;
            case 5:
                System.out.println(request.currentUsers());
                break;
            case 6:
                System.out.println(request.runningProcesses());
                break;
            case 7:
                System.out.println(request.exitProgram());
                break;
        }
    }
}