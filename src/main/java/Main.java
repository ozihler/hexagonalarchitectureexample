public class Main {


    public static void main(String[] args) {
        InMemoryToDoListRepository repository = new InMemoryToDoListRepository();
        Console console = new Console();

        ToDoList toDoList = repository.findByName("Daily Routine");
        console.show(toDoList);

        Item nextItem;
        while ((nextItem = console.enterNextItemToList()) != null) {
            toDoList.add(nextItem);
            repository.save(toDoList);
            console.show(toDoList);
        }

        console.finish();
    }
}
