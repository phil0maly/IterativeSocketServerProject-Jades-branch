import java.time.LocalDateTime;
import java.lang.ProcessBuilder;

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
}
