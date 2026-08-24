package dk.zealand.ui;

import dk.zealand.domain.Dish;
import java.util.List;

public class ConsoleMenu {
    public void showMainMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    public void showDishes(List<Dish> dishes) {
        System.out.println("Retter:");

        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            System.out.printf("%d. %s%n", i + 1, dish);
        }
    }
}
