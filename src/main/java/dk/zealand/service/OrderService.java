package dk.zealand.service;

import dk.zealand.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final int MAX_ORDERS = 10;

    private final MenuService menuService;
    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    public OrderService(MenuService menuService) {
        this.menuService = menuService;
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    public Order createOrder(String selectedDishInput, String quantityInput) {
        int selectedDish;
        int quantity;

        try {
            selectedDish = Integer.parseInt(selectedDishInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ugyldig ret. Vælg en af de tre gyldige retter.");
        }

        try {
            quantity = Integer.parseInt(quantityInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ugyldigt antal. Indtast et positivt tal.");
        }

        return createOrder(selectedDish, quantity);
    }

    public Order createOrder(int selectedDish, int quantity) {
        if (selectedDish < 1 || selectedDish > menuService.getDishes().size()) {
            throw new IllegalArgumentException("Ugyldig ret. Vælg en af de tre gyldige retter.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Ugyldigt antal. Indtast et positivt tal.");
        }

        if (orders.size() >= MAX_ORDERS) {
            throw new IllegalArgumentException("Der kan højst gemmes ti bestillinger.");
        }

        String dishName = menuService.getDishes().get(selectedDish - 1).getName();
        Order order = new Order(nextId++, dishName, quantity, "MODTAGET");
        orders.add(order);
        return order;
    }
}
