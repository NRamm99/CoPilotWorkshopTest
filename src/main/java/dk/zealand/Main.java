package dk.zealand;

import dk.zealand.domain.Order;
import dk.zealand.service.MenuService;
import dk.zealand.service.OrderService;
import dk.zealand.ui.ConsoleMenu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService(menuService);
        ConsoleMenu consoleMenu = new ConsoleMenu();
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            consoleMenu.showMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> consoleMenu.showDishes(menuService.getDishes());
                case "2" -> createOrder(scanner, consoleMenu, orderService);
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void createOrder(Scanner scanner, ConsoleMenu consoleMenu, OrderService orderService) {
        consoleMenu.promptForDishSelection();
        String dishChoice = scanner.nextLine().trim();

        consoleMenu.promptForQuantity();
        String quantityInput = scanner.nextLine().trim();

        try {
            Order order = orderService.createOrder(dishChoice, quantityInput);
            consoleMenu.showOrder(order);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
