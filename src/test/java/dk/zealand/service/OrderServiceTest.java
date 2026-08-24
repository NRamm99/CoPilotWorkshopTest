package dk.zealand.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dk.zealand.domain.Order;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    @Test
    void shouldCreateValidOrder() {
        OrderService orderService = new OrderService(new MenuService());

        Order order = orderService.createOrder("2", "2");

        assertEquals(1, order.getId());
        assertEquals("Sprøde fritter", order.getDishName());
        assertEquals(2, order.getQuantity());
        assertEquals("MODTAGET", order.getStatus());
        assertEquals(1, orderService.getOrders().size());
    }

    @Test
    void shouldRejectInvalidDish() {
        OrderService orderService = new OrderService(new MenuService());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder("9", "1")
        );

        assertEquals("Ugyldig ret. Vælg en af de tre gyldige retter.", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidQuantity() {
        OrderService orderService = new OrderService(new MenuService());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder("1", "0")
        );

        assertEquals("Ugyldigt antal. Indtast et positivt tal.", exception.getMessage());
    }

    @Test
    void shouldRejectTextInsteadOfQuantity() {
        OrderService orderService = new OrderService(new MenuService());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder("1", "tekst")
        );

        assertEquals("Ugyldigt antal. Indtast et positivt tal.", exception.getMessage());
    }

    @Test
    void shouldCreateUniqueOrderIds() {
        OrderService orderService = new OrderService(new MenuService());

        Order firstOrder = orderService.createOrder("1", "1");
        Order secondOrder = orderService.createOrder("2", "2");

        assertNotEquals(firstOrder.getId(), secondOrder.getId());
    }

    @Test
    void shouldRejectOrderWhenLimitIsReached() {
        OrderService orderService = new OrderService(new MenuService());

        for (int i = 0; i < 10; i++) {
            orderService.createOrder("1", "1");
        }

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder("1", "1")
        );

        assertEquals("Der kan højst gemmes ti bestillinger.", exception.getMessage());
    }
}
