import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryToDoListRepository {

    private final Map<String, ToDoList> toDoLists;

    public InMemoryToDoListRepository() {
        this.toDoLists = new HashMap<>();
        toDoLists.put("Daily Routine", new ToDoList(List.of(Item.of("Grocery Shopping"), Item.of("Fitness"))));
    }

    void save(ToDoList toDoList) {
        toDoLists.put("Daily Routine", toDoList);
    }


    public ToDoList findByName(String name) {
        return toDoLists.get(name);
    }
}
