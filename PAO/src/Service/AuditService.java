package Service;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class AuditService {
    FileWriter writer;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public void logAction(String action) throws IOException {
        if (writer != null) {
            writer.append(action);
            writer.append(": ");
            writer.append(formatter.format(LocalDateTime.now()));
            writer.append("\n");
            writer.flush();
        } else {
            throw new IOException("FileWriter is not initialized. Please check the constructor.");
        }
    }

    public AuditService() throws IOException {
        this.writer = new FileWriter("src/data/audit.csv");
    }

    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
