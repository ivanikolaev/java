package pract2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Human {
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }
    public int getAgeWeightSum(){
        return age + weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Human(" +
                "Возраст: " + age +
                ", имя: '" + firstName + '\'' +
                ", фамилия: '" + lastName + '\'' +
                ", дата рождения: " + birthDate +
                ", вес: " + weight +
                ')';
    }

    public static void main(String[] args) {
        List<Human> humans = new ArrayList<>();

        humans.add(new Human(34, "Иван", "Иванов", LocalDate.of(1990, 1, 1), 75));
        humans.add(new Human(28, "Петр", "Савельев", LocalDate.of(1995, 4, 29), 80));
        humans.add(new Human(31, "Мария", "Баранова", LocalDate.of(1992, 3, 10), 52));
        humans.add(new Human(26, "Александр", "Бобков", LocalDate.of(1997, 5, 17), 90));
        humans.add(new Human(24, "Арсений", "Ситницкий", LocalDate.of(2000, 2, 15), 63));
        humans.add(new Human(19, "Сергей", "Кузнецов", LocalDate.of(2004, 11, 30), 77));

        System.out.println("Сортировка по сумме веса и возраста: ");
        Stream<Human> sorted = humans.stream();
        sorted.sorted(Comparator.comparingInt(Human::getAgeWeightSum))
                .toList()
                .forEach(System.out::println);
        System.out.print('\n');

        System.out.println("Фильтрация по весу, кратному 5: ");
        Stream<Human> filtered = humans.stream();
        filtered.filter(human -> human.getWeight() % 5 == 0)
                .toList()
                .forEach(System.out::println);
        System.out.print('\n');

        System.out.println("Выбор первых 4 элементов: ");
        Stream<Human> picked = humans.stream();
        picked.limit(4)
                .toList()
                .forEach(System.out::println);
        System.out.print('\n');

        System.out.println("Конкатенация имен через пробел: ");
        String concatenated = humans.stream()
                .map(human -> human.getFirstName())
                .collect(Collectors.joining(" "));
        System.out.println(concatenated);
    }
}
