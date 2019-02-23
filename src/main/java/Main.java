import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> todoList = Files.readAllLines(Path.of(Main.class.getResource("todolist.txt").toURI()));

        String nextLine;
        do {
            out.println("Enter next item to todo list:");
            nextLine = reader.readLine();

            if (nextLine != null && !nextLine.isBlank()) {
                todoList.add(nextLine);
            } else {
                out.println("Finished adding items to todo list");
            }

            out.println("All items in todo list:");
            out.println("=======================");
            for (String s : todoList) {
                out.println(s);
            }
            out.println("=======================");
        } while (nextLine != null && !nextLine.isBlank());
    }
}
