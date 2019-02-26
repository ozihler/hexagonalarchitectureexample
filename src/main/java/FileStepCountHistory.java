import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileStepCountHistory implements IObtainStepCounts, IStoreStepCounts {
    private Map<LocalDate, Integer> stepCountHistory;
    private String stepCountFile;

    public FileStepCountHistory(String stepCountFile) {
        this.stepCountHistory = loadStepCountHistoryFrom(stepCountFile);
        this.stepCountFile = stepCountFile;
    }

    static Map<LocalDate, Integer> loadStepCountHistoryFrom(String stepCountFile) {
        Map<LocalDate, Integer> stepCountHistory = new HashMap<>();
        try {
            List<String> stepCountEntries = Files.readAllLines(Path.of(FileStepCountHistory.class.getResource(stepCountFile).toURI()));
            for (String stepCountEntry : stepCountEntries) {
                String[] entryParts = stepCountEntry.split(",");
                String[] date = entryParts[0].split("\\.");
                LocalDate localDate = LocalDate.of(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]),
                        Integer.parseInt(date[0]));
                stepCountHistory.put(localDate, Integer.parseInt(entryParts[1]));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return stepCountHistory;
    }

    @Override
    public int loadTodaysStepCount() {
        return stepCountHistory.get(LocalDate.now());
    }

    @Override
    public void updateTodaysStepCount(int stepCount) {
        this.stepCountHistory.put(LocalDate.now(), stepCount);
        List<String> entries = new ArrayList<>();
        for (Map.Entry<LocalDate, Integer> entry : stepCountHistory.entrySet()) {
            LocalDate date = entry.getKey();
            String formattedEntry = String.format("%d.%s.%d,%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear(), entry.getValue());
            entries.add(formattedEntry);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(getClass().getResource(stepCountFile).toURI()))) {
            for (int i = 0; i < entries.size(); i++) {
                writer.write(entries.get(i));
                if (i != (entries.size() - 1)) {
                    writer.newLine();
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
