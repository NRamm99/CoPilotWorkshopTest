package dk.zealand;

import dk.zealand.service.MenuService;
import dk.zealand.ui.ConsoleMenu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService();
        ConsoleMenu consoleMenu = new ConsoleMenu();
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            consoleMenu.showMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> consoleMenu.showDishes(menuService.getDishes());
                case "2" -> System.out.println(
                        "Oprettelse af bestillinger er endnu ikke implementeret."
                );
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }
}
