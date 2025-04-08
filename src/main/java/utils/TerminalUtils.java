package utils;

public class TerminalUtils {

    public static void executeCommand(String... command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                ReportManager.warn("Command failed with exit code:", String.valueOf(exitCode));
            }
            ReportManager.info("Command executed successfully", String.join(" ", command));
        } catch (Exception e) {
            ReportManager.error("Failed to execute command: " + String.join(" ", command), e.getMessage());
        }
    }
}
