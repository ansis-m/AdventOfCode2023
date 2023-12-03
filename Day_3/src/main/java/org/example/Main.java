package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {


    int[][] matrix = {{-1, -1}, {-1,0}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
    List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        new Main().adventOfCode();
    }

    private void adventOfCode() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("schematic.txt")).getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            lines = br.lines().toList();
            int sum = 0;

            for(int row = 0; row < lines.size(); row++) {
                String[] numbers = lines.get(row).split("\\D+");
                int index = -1;

                for (String number : numbers) {
                    index = lines.get(row).indexOf(number, index);
                    int len = number.length();


                    for(int k = index; k < index + len; k++) {
                        if(adjacentSymbol(row, k)) {
                            sum += Integer.parseInt(number);
                            break;
                        }
                    }
                    index += len;
                }
            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean adjacentSymbol(int row, int k) {
        for(int i = 0; i < 8; i++) {
            int x = row + matrix[i][1];
            int y = k + matrix[i][0];

            try{
                if (isValidChar(lines.get(x).charAt(y))) {
                    return true;
                }
            } catch (Exception ignored){}
        }
        return false;
    }

    private boolean isValidChar(char character) {
        return (character < '0' || character > '9') && character != '.';
    }
}