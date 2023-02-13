package by.harbuzau;

import by.harbuzau.model.Animal;
import by.harbuzau.model.Car;
import by.harbuzau.model.Flower;
import by.harbuzau.model.House;
import by.harbuzau.model.Person;
import by.harbuzau.task16.Cat;
import by.harbuzau.task16.MyUtil;
import by.harbuzau.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals().stream()
                .filter(a -> a.getAge() >= 10 && a.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.toList());
        List<List<Animal>> zoo = IntStream.rangeClosed(0, (animals.size() - 1) / 7)
                .mapToObj(i -> animals.subList(i * 7, Math.min((i + 1) * 7, animals.size())))
                .collect(Collectors.toList());
        zoo.get(2).forEach(System.out::println);

        // Альтернативное короткое решение
        List<Animal> animals2 = Util.getAnimals();
        animals2.stream()
                .filter(a -> a.getAge() >= 10 && a.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(14L)
                .limit(7L)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> "Japanese".equals(a.getOrigin()))
                .peek(a -> a.setBread(a.getBread().toUpperCase()))
                .filter(a -> "Female".equals(a.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> a.getAge() > 30 && a.getOrigin().startsWith("A"))
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream().filter(a -> "Female".equals(a.getGender())).count();
        System.out.println(count);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean bool = animals.stream()
                .filter(a -> a.getAge() >= 20 && a.getAge() <= 30)
                .anyMatch(a -> "Hungarian".equals(a.getOrigin()));
        System.out.println(bool);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean bool = animals.stream()
                .allMatch(a -> "Male".equals(a.getGender()) || "Female".equals(a.getGender()));
        System.out.println(bool);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean bool = animals.stream().noneMatch(a -> "Oceania".equals(a.getOrigin()));
        System.out.println(bool);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100L)
                .mapToInt(Animal::getAge)
                .max()
                .getAsInt();
        System.out.println(maxAge);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int minLength = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(a -> a.length)
                .min()
                .getAsInt();
        System.out.println(minLength);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int fullAge = animals.stream().mapToInt(Animal::getAge).sum();
        System.out.println(fullAge);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        double averageAge = animals.stream()
                .filter(a -> "Indonesian".equals(a.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .getAsDouble();
        System.out.println(averageAge);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(p -> "Male".equals(p.getGender()))
                .filter(p -> ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) >= 18)
                .filter(p -> ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) < 27)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200L)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();

        // Список людей из госпиталя
        List<Person> person1 = houses.stream()
                .filter(h -> "Hospital".equals(h.getBuildingType()))
                .map(House::getPersonList)
                .flatMap(x -> x.stream().limit(500L))
                .collect(Collectors.toList());

        // Список детей и стариков
        List<Person> person2 = houses.stream()
                .map(House::getPersonList)
                .flatMap(x -> x.stream()
                        .filter(p -> ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) < 18 ||
                                ("Male".equals(p.getGender()) &&
                                ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) >= 63) ||
                                ("Female".equals(p.getGender()) &&
                                ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) >= 58)))
                .limit(500L)
                .collect(Collectors.toList());
        person2.removeAll(person1); // На случай, если люди уже были в списке из госпиталя

        person1.addAll(person2);
        person1.stream().limit(500L).forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();

        // 1. Туркменистан
        List<Car> cars1 = cars.stream()
                .filter(c -> "Jaguar".equals(c.getCarMake()) || "White".equals(c.getColor()))
                .collect(Collectors.toList());
        cars.removeAll(cars1); // Здесь и далее из общего списка исключаются отобранные автомобили

        // 2. Узбекистан
        List<Car> cars2 = cars.stream()
                .filter(c -> c.getMass() <= 1500 ||
                        "BMW".equals(c.getCarMake()) ||
                        "Lexus".equals(c.getCarMake()) ||
                        "Chrysler".equals(c.getCarMake()) ||
                        "Toyota".equals(c.getCarMake()))
                .collect(Collectors.toList());
        cars.removeAll(cars2);

        // 3. Казахстан
        List<Car> cars3 = cars.stream()
                .filter(c -> "Black".equals(c.getColor()) &&
                        c.getMass() >= 4000 ||
                        "GMC".equals(c.getCarMake()) ||
                        "Dodge".equals(c.getCarMake()))
                .collect(Collectors.toList());
        cars.removeAll(cars3);

        // 4. Кыргызстан
        List<Car> cars4 = cars.stream()
                .filter(c -> c.getReleaseYear() < 1982 ||
                        "Civic".equals(c.getCarMake()) ||
                        "Cherokee".equals(c.getCarMake()))
                .collect(Collectors.toList());
        cars.removeAll(cars4);

        // 5. Россия
        List<Car> cars5 = cars.stream()
                .filter(c -> !"Yellow".equals(c.getColor()) &&
                        !"Red".equals(c.getColor()) &&
                        !"Green".equals(c.getColor()) &&
                        !"Blue".equals(c.getColor()) ||
                        c.getPrice() > 40000)
                .collect(Collectors.toList());
        cars.removeAll(cars5);

        // 6. Монголия
        List<Car> cars6 = cars.stream()
                .filter(c -> c.getVin().contains("59"))
                .collect(Collectors.toList());
        cars.removeAll(cars6);

        // Выручка по регионам и общая выручка
        long totalRev1 = totalRevenue(cars1);
        long totalRev2 = totalRevenue(cars2);
        long totalRev3 = totalRevenue(cars3);
        long totalRev4 = totalRevenue(cars4);
        long totalRev5 = totalRevenue(cars5);
        long totalRev6 = totalRevenue(cars6);
        long fullTotalRev = totalRev1 + totalRev2 + totalRev3 + totalRev4 + totalRev5 + totalRev6;

        System.out.println("Выручка в регионе 1 Туркменистан: " + totalRev1 + " $");
        System.out.println("Выручка в регионе 2 Узбекистан: " + totalRev2 + " $");
        System.out.println("Выручка в регионе 3 Казахстан: " + totalRev3 + " $");
        System.out.println("Выручка в регионе 4 Кыргызстан: " + totalRev4 + " $");
        System.out.println("Выручка в регионе 5 Россия: " + totalRev5 + " $");
        System.out.println("Выручка в регионе 6 Монголия: " + totalRev6 + " $");
        System.out.println("Общая выручка: " + fullTotalRev + " $");
    }

    // Транспортные расходы
    private static double transportCosts(List<Car> carList) {
        return (double) carList.stream().mapToInt(Car::getMass).sum() / 1000 * 7.14;
    }

    // Общая стоимость автомобилей
    private static double fullPrice(List<Car> carList) {
        return carList.stream().mapToInt(Car::getPrice).sum();
    }

    // Выручка
    private static long totalRevenue(List<Car> carList) {
        return (long) (fullPrice(carList) - transportCosts(carList));
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        double sum = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Comparator.comparing(Flower::getPrice))
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(fl -> fl.getCommonName().charAt(0) >= 'C' &&
                        fl.getCommonName().charAt(0) <= 'S')
                .filter(Flower::isShadePreferred)
                .filter(fl -> fl.getFlowerVaseMaterial().contains("Glass") ||
                        fl.getFlowerVaseMaterial().contains("Aluminum") ||
                        fl.getFlowerVaseMaterial().contains("Steel"))
                .mapToDouble(fl -> fl.getPrice() + fl.getWaterConsumptionPerDay() / 1000 * 365 * 5 * 1.39)
                .sum();
        System.out.println(sum);
    }

    private static void task16() throws IOException {
        List<Person> people = Util.getPersons();
        List<Cat> cats = MyUtil.getCats();

        boolean result = people.stream()

                // Поиск людей подходящего возраста
                .filter(pers -> ("Male".equals(pers.getGender()) &&
                        ChronoUnit.YEARS.between(pers.getDateOfBirth(), LocalDate.now()) >= 63) ||
                        ("Female".equals(pers.getGender()) &&
                        ChronoUnit.YEARS.between(pers.getDateOfBirth(), LocalDate.now()) >= 58))

                // Есть ли Tobey или Brittany, у которых чёрный кот/кошка
                .anyMatch(pers -> cats.stream()
                        .filter(cat -> "Black".equals(cat.getColor()))
                        .filter(cat -> "Tobey".equals(cat.getOwnerName()) || "Brittany".equals(cat.getOwnerName()))
                        .map(Cat::getOwnerName).collect(Collectors.toList()).contains(pers.getFirstName()));

        System.out.println(result ? "Роботы обратились в бегство!" : "Спасения нет!");
    }
}