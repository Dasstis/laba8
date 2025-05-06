package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Часть 1: Нахождение наиболее часто встречающегося слова
        String[] words = {"Крест", "Рука", "Крест", "Нога", "Автомат", "Автомат", "Автомат", "Крест", "Пулемет", "Пулемет", "Пулемет"};

        System.out.println(
                "Наиболее часто встречающиеся слово(а): " +
                        Arrays.stream(words)
                                .collect(Collectors.collectingAndThen(
                                        Collectors.groupingBy(word -> word, Collectors.counting()),
                                        wordCounts -> wordCounts.entrySet().stream()
                                                .filter(e -> e.getValue().equals(
                                                        Collections.max(wordCounts.values())))
                                                .filter(e -> e.getKey().length() ==
                                                        wordCounts.entrySet().stream()
                                                                .filter(inner -> inner.getValue().equals(
                                                                        Collections.max(wordCounts.values())))
                                                                .mapToInt(inner -> inner.getKey().length())
                                                                .max().orElse(0))
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.joining(", ", "", "."))
                                ))
        );

        // Часть 2: Создание и обработка массива сотрудников
        Set<Employee> employees = new HashSet<>();
        Collections.addAll(employees,
                new Employee("Леша Сырых", 30, "Мужской", 50000),
                new Employee("Соболев Илья", 91, "Мужской", 60000),
                new Employee("Злобина Матвея", 31, "Женский", 55000),
                new Employee("Борисов Игорь", 90, "Мужской", 70000),
                new Employee("Рабинович Игорь", 35, "Мужской", 65000)
        );

        System.out.println(
                        employees.stream()
                                .filter(e -> "Мужской".equals(e.getGender()))
                                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                                .map(Employee::getName)
                                .collect(Collectors.joining(", ", "Самых страршиш сотрудников зовут: ", "."))
        );
    }
}