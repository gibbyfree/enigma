import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Pair;
import models.Plugboard;
import static consts.Constants.ALPHABET;

public class Main {

    public static List<Pair> fillPlugBoard(List<Pair> plugBoard, String line) {
        String[] swaps = line.split(" ");

        for (String swap : swaps) {
            String[] splitSwap = swap.split(",");
            String x = splitSwap[0].substring(1);
            String y = splitSwap[1].substring(0, 1);

            int xInt = ALPHABET.indexOf(x.charAt(0));
            int yInt = ALPHABET.indexOf(y.charAt(0));

            Pair toAdd = new Pair(xInt, yInt);
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

    public static List<Character> parseInput(String fileName) throws IOException {
        File input = new File(fileName);
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            while((line = br.readLine()) != null ) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Your input file could not be found. Check the readme and try again.");
            e.printStackTrace();
        }

        String contents = sb.toString().toUpperCase();
        // This will allow us to replace our spaces at the end of encryption. 
        contents = contents.replace(" ", "+");

        List<String> contentsList = Arrays.asList(contents.split("(?!^)"));
        List<Character> output = new ArrayList<Character>();

        for(String s : contentsList) {
            char c = s.charAt(0);
            if(Character.isAlphabetic(c) || c == '+') {
                output.add(s.charAt(0));
            }
        }

        return output;
    }

    public static List<Character> encrypt(List<Character> toEncrypt, Plugboard pb) {
        List<Character> encrypted = new ArrayList<Character>();

        for(Character c : toEncrypt) {
            if(c == '+') {
                encrypted.add(c);
            }
            else {
                int alphaIndex = ALPHABET.indexOf(c);
                int afterPb = pb.translate(alphaIndex);
                encrypted.add(ALPHABET.get(afterPb));
            }
        }

        return encrypted;
    }

    public static void main(String[] args) throws IOException {
        // Read the file and fill variables.
        String dayKeyFileName = args[0];
        File dayKey = new File(dayKeyFileName);

        List<Pair> plugBoard = new ArrayList<Pair>();
        List<Integer> rotors = new ArrayList<Integer>();
        List<String> keySettings = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(dayKey))) {
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

        String inputFileName = args[1];
        List<Character> toEncrypt = parseInput(inputFileName);


        Plugboard pb = new Plugboard(plugBoard);
        List<Character> encrypted = encrypt(toEncrypt, pb);

        for(Character c : encrypted) {
            System.out.print(c);
        }
        System.out.print("\n");

    }

}
