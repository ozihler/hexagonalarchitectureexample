import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        Map<String, List> toDoLists = new HashMap<>();
        toDoLists.put("Daily Routine", new ArrayList<>(List.of(Item.of("Grocery Shopping"), Item.of("Fitness"))));

        List<Item> toDoList = toDoLists.get("Daily Routine");
        show(toDoList);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Item nextItem;
        do {
            System.out.println("Enter next item to todo list:");
            String nextLine = reader.readLine();

            if (nextLine == null || nextLine.isBlank()) {
                nextItem = null;
            } else {
                nextItem = new Item(nextLine);
            }

            toDoList.add(nextItem);
            toDoLists.put("Daily Routine", toDoList);
            show(toDoList);
        } while (nextItem != null);

        System.out.println("Finished adding items to todo list");
    }

    private static void show(List<Item> toDoList) {
        System.out.println("All items in todo list:");
        System.out.println("=======================");
        toDoList.forEach(System.out::println);
        System.out.println("=======================");
    }
}
