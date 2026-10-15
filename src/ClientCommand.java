import java.time.LocalDateTime;

public class ClientCommand {
    Integer request;

    public ClientCommand(Integer request) {
        this.request = request;
    }

    protected LocalDateTime dateTime() {
        return java.time.LocalDateTime.now();
    }

    protected LocalDateTime upTime() {
        return java.time.LocalDateTime.now();
    }

    protected LocalDateTime memUse() {
        return java.time.LocalDateTime.now();
    }

    protected LocalDateTime netStat() {
        return java.time.LocalDateTime.now();
    }

    protected LocalDateTime currentUsers() {
        return java.time.LocalDateTime.now();
    }

    protected LocalDateTime runningProcesses() {
        return java.time.LocalDateTime.now();
    }

    protected LocalDateTime exitProgram() {
        return java.time.LocalDateTime.now();
    }
}
