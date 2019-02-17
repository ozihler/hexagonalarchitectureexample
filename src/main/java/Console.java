import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

class Console {

    private final BufferedReader in;
    private PrintStream out;

    public Console() {
        out = System.out;
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }

    void show(ToDoList toDoList) {
        out.println("All items in todo list:");
        out.println("=======================");
        toDoList.getItems()
                .stream()
                .map(Item::getItem)
                .forEach(out::println);
        out.println("=======================");
    }

    public Item enterNextItemToList() {
        out.println("Enter next item to todo list:");
        String nextLine = null;
        try {
            nextLine = this.in.readLine();

            if (nextLine == null || nextLine.isBlank()) {
                return null;
            }
            return new Item(nextLine);
        } catch (IOException e) {
            return null;
        }
    }

    public void finish() {
        out.println("Finished adding items to todo list");
    }
}
