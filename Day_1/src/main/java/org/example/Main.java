package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Main {


    private static final List<String> digits = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");


    public static void main(String[] args) {
        new Main().adventOfCode();
    }

    private void adventOfCode() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            int result = 0;
            int updatedResult = 0;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                int cv = getCalibrationValue(line);
                System.out.println("calibration value: " + cv);
                result += cv;

                int updatedCalibrationValue = getUpdatedCalibrationValue(line);
                if (cv != updatedCalibrationValue) {
                    System.out.println("~~~~~~~~~~~~~~~\n\nUpdated value: " + updatedCalibrationValue + "\n\n~~~~~~~~~~~~~~~~");
                }
                updatedResult += updatedCalibrationValue;
            }

            System.out.println("Result: " + result);
            System.out.println("Updated Result: " + updatedResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getUpdatedCalibrationValue(String line) {

        char currentDigit = 'x';
        char firstDigit = 'x';

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                currentDigit = line.charAt(i);

            } else {
                int digit = startsWithDigit(line.substring(i));
                if (digit != -1) {
                    currentDigit = (char) ('0' + digit);
                }
            }
            if (firstDigit == 'x' && currentDigit != 'x') {
                firstDigit = currentDigit;
            }
        }
        return (firstDigit - '0') * 10 + currentDigit - '0';
    }

    private static int startsWithDigit(String substring) {

        for (int i = 0; i < digits.size(); i++) {
            if(substring.startsWith(digits.get(i))) {
                return i + 1;
            }
        }

        return -1;
    }

    private static int getCalibrationValue(String line) {

        char[] chars = line.toCharArray();
        char currentDigit = '0';
        char firstDigit = 'x';

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                currentDigit = chars[i];
                if (firstDigit == 'x') {
                    firstDigit = currentDigit;
                }
            }
        }
        return (firstDigit - '0') * 10 + currentDigit - '0';
    }
}