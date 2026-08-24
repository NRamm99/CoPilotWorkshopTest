package dk.zealand.service;

import dk.zealand.domain.Dish;
import java.util.List;

public class MenuService {
    private final List<Dish> dishes;

    public MenuService() {
        dishes = List.of(
                new Dish("Festivalburger", 59),
                new Dish("Sprøde fritter", 35),
                new Dish("Vegansk bowl", 65)
        );
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
