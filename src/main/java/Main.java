import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        Map<String, List<String>> toDoLists = new HashMap<>();
        toDoLists.put("Daily Routine", new ArrayList<>(List.of("Grocery Shopping", "Fitness")));

        List<String> toDoList = toDoLists.get("Daily Routine");
        show(toDoList);


        String nextLine;
        do {
            out.println("Enter next item to todo list:");
            nextLine = reader.readLine();

            if (nextLine != null && !nextLine.isBlank()) {
                toDoLists.get("Daily Routine").add(nextLine);
            }

            show(toDoList);
        } while (nextLine != null && !nextLine.isBlank());

        out.println("Finished adding items to todo list");
    }

    private static void show(List<String> toDoList) {
        out.println("All items in todo list:");
        out.println("=======================");
        toDoList.forEach(out::println);
        out.println("=======================");
    }
}
