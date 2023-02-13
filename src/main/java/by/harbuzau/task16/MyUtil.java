package by.harbuzau.task16;

import java.util.List;

public class MyUtil {

    public static List<Cat> getCats() {
        return List.of(
                new Cat("Barsik", "White", "Sofia"),
                new Cat("Murka", "Black", "Brittany"),
                new Cat("Boris", "Grey", "Kimmy"),
                new Cat("Alex", "Red", "Alexina"),
                new Cat("Dasha", "White", "Fredrick")
        );
    }
}