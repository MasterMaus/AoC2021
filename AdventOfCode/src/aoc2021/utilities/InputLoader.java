package aoc2021.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputLoader {

    public static ArrayList<String> toStringList(String filepath) {
        ArrayList<String> output = new ArrayList<>();
        File file = new File(filepath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return output;
    }

    public static ArrayList<Integer> toIntegerList(String filepath) {
        ArrayList<Integer> output = new ArrayList<>();
        File file = new File(filepath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return output;
    }

    public static String asString(String filepath, String delimiter) {
        String output = "";
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if(nextLine.isEmpty()) {
                    output = output + "\n";
                } else {
                    output = output + nextLine + delimiter;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return output;
    }
}
