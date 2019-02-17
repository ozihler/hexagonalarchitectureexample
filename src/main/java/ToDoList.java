import java.util.ArrayList;
import java.util.List;

public class ToDoList {


    private List<Item> toDoListItems = new ArrayList<>();

    public ToDoList(List<Item> toDoListItems) {
        this.toDoListItems.addAll(toDoListItems);
    }

    List<Item> getItems() {
        return toDoListItems;
    }

    void add(Item item) {
        this.toDoListItems.add(item);
    }

}
