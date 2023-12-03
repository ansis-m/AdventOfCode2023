package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        new Main().adventOfCode();
    }

    private void adventOfCode() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("games.txt").getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> lines = br.lines().toList();

            int result = IntStream.range(0, lines.size())
                    .filter(i -> gamePossible(lines.get(i)))
                    .map(i -> i + 1)
                    .sum();

            int sumOfPowersOfMinimalSets = IntStream.range(0, lines.size())
                    .map(i -> powerOfMinimalSet(lines.get(i)))
                    .sum();

            System.out.println("Result: " + result);
            System.out.println("Sum of powers of minimal sets: " + sumOfPowersOfMinimalSets);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int powerOfMinimalSet(String line) {

        AtomicInteger red = new AtomicInteger();
        AtomicInteger green = new AtomicInteger();
        AtomicInteger blue = new AtomicInteger();

        Arrays.stream(line.split(": ")[1].split("[,;]\\s*")).forEach(s -> {
            int number = Integer.parseInt(s.split(" ")[0]);
            String color = s.split(" ")[1];

            switch (color) {
                case "red" -> {
                    if (number > red.get())
                        red.set(number);
                }
                case "green" -> {
                    if (number > green.get())
                        green.set(number);
                }
                case "blue" -> {
                    if (number > blue.get())
                        blue.set(number);
                }
                default -> System.out.println("\n\n\nBad color: " + color + "\n\n\n");
            }
        });

        return red.get() * green.get() * blue.get();

    }

    private boolean gamePossible(String line) {
        String[] games = line.split(": ")[1].split("; ");

        return Stream.of(games)
                .allMatch(game -> Stream.of(game.split(", "))
                        .allMatch(this::isValidGame));
    }

    private boolean isValidGame(String game) {
        int number = Integer.parseInt(game.split(" ")[0]);
        String color = game.split(" ")[1];

        switch (color) {
            case "red":
                return number >= 0 && number <= 12;
            case "green":
                return number >= 0 && number <= 13;
            case "blue":
                return number >= 0 && number <= 14;
            default:
                System.out.println("\n\n\nBad color: " + color + "\n\n\n");
                return false;
        }
    }
}
