import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Pair;

public class Main {

    public static List<Pair<String, String>> fillPlugBoard(List<Pair<String, String>> plugBoard, String line) {
        String[] swaps = line.split(" ");

        for (String swap : swaps) {
            String[] splitSwap = swap.split(",");
            String x = splitSwap[0].substring(1);
            String y = splitSwap[1].substring(0, 1);

            Pair<String, String> toAdd = new Pair<String, String>(x, y);
            plugBoard.add(toAdd);
        }

        return plugBoard;
    }

    public static List<Integer> fillRotors(List<Integer> rotors, String line) {
        String[] rotorArr = line.split(" ");

        for (String rotor : rotorArr) {
            rotors.add(Integer.parseInt(rotor));
        }

        return rotors;
    }

    public static List<String> fillKeySettings(List<String> keySettings, String line) {
        String[] rotorKey = line.split(" ");

        return Arrays.asList(rotorKey);
    }

    public static void main(String[] args) throws IOException {
        // Read the file and fill variables.
        String fileName = args[0];
        File input = new File(fileName);

        List<Pair<String, String>> plugBoard = new ArrayList<Pair<String, String>>();
        List<Integer> rotors = new ArrayList<Integer>();
        List<String> keySettings = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            for (int linesRead = 0; linesRead < 3; linesRead++) {
                String line = br.readLine();

                if (line != null && linesRead == 0) {
                    plugBoard = fillPlugBoard(plugBoard, line);
                } else if (line != null && linesRead == 1) {
                    rotors = fillRotors(rotors, line);
                } else if (line != null && linesRead == 2) {
                    keySettings = fillKeySettings(keySettings, line);
                } else {
                    System.out.println("Your day key was not formatted properly. Check the readme and try again.");
                    System.exit(0);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The input file could not be found at your given location.");
            e.printStackTrace();
        }


    }

}
