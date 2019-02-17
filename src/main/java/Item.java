public class Item {
    private String item;

    public Item(String item) {
        this.item = item;
    }

    public static Item of(String item) {
        return new Item(item);
    }

    public String getItem() {
        return item;
    }
}
