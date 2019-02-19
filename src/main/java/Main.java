import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static InputStream in = System.in;
    static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        Map<String, List<String>> toDoLists = new HashMap<>();
        toDoLists.put("Daily Routine", new ArrayList<>(List.of("Grocery Shopping", "Fitness")));

        List<String> toDoList = toDoLists.get("Daily Routine");
        show(toDoList);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String nextLine;
        do {
            out.println("Enter next item to todo list:");
            nextLine = reader.readLine();

            if (nextLine != null && !nextLine.isBlank()) {
                toDoLists.get("Daily Routine").add(nextLine);
            }

            show(toDoList);
        } while (nextLine != null && !nextLine.isBlank());

        System.out.println("Finished adding items to todo list");
    }

    private static void show(List<String> toDoList) {
        System.out.println("All items in todo list:");
        System.out.println("=======================");
        toDoList.forEach(System.out::println);
        System.out.println("=======================");
    }
}
