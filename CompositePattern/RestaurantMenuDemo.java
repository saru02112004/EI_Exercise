import java.util.ArrayList;
import java.util.List;

// Component
interface MenuComponent {
    void display();
}

// Leaf (Menu Item)
class MenuItem implements MenuComponent {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void display() {
        System.out.printf("  - %s: $%.2f%n", name, price);
    }
}

// Composite (Menu)
class Menu implements MenuComponent {
    private String name;
    private List<MenuComponent> items = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public void add(MenuComponent item) {
        items.add(item);
    }

    @Override
    public void display() {
        System.out.println("\n" + name + " Menu:");
        for (MenuComponent item : items) {
            item.display();
        }
    }
}

// Usage
public class RestaurantMenuDemo {
    public static void main(String[] args) {
        Menu breakfastMenu = new Menu("Breakfast");
        breakfastMenu.add(new MenuItem("Pancakes", 5.99));
        breakfastMenu.add(new MenuItem("Omelette", 6.99));

        Menu lunchMenu = new Menu("Lunch");
        lunchMenu.add(new MenuItem("Burger", 8.99));
        lunchMenu.add(new MenuItem("Salad", 7.99));

        Menu dinnerMenu = new Menu("Dinner");
        dinnerMenu.add(new MenuItem("Steak", 19.99));
        dinnerMenu.add(new MenuItem("Pasta", 12.99));

        Menu allMenus = new Menu("All Menus");
        allMenus.add(breakfastMenu);
        allMenus.add(lunchMenu);
        allMenus.add(dinnerMenu);

        allMenus.display();
    }
}
