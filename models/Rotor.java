package models;

import java.util.List;

import static consts.Constants.ROTOR_1;
import static consts.Constants.ROTOR_2;
import static consts.Constants.ROTOR_3;
import static consts.Constants.UKW_B;
import static consts.Constants.ALPHABET;

public class Rotor {
    private int rotor;
    private int offset;
    private List<Character> wireSpec;

    public Rotor(int rotor, int offset) {
        if (rotor == 1) {
            this.wireSpec = ROTOR_1;
        } else if (rotor == 2) {
            this.wireSpec = ROTOR_2;
        } else if (rotor == 3) {
            this.wireSpec = ROTOR_3;
        } else if (rotor == 0) {
            this.wireSpec = UKW_B;
        } else {
            System.out.println("Your day key contains an invalid rotor number. Check the readme and try again.");
            System.exit(1);
        }
        this.rotor = rotor;
        this.offset = offset;
    }

    public int translate(int i) {
        int newIndex = i + offset;
        while(newIndex > 25) {
            newIndex -= 25;
        }
        char before = ALPHABET.get(i);
        char after = this.wireSpec.get(newIndex - 1);
        System.out.println("The current passes through rotor " + this.rotor + ". " + before + " becomes " + after + ".");
        return ALPHABET.indexOf(after);
    }

    public void rotate() {
        if(offset >= 25) {
            offset = 0;
        }
        else {
            this.offset++;
        }
        System.out.println("Rotor " + this.rotor + " rotates.");
    }

    public Character getTop() {
        return ALPHABET.get(this.offset);
    }

    public int getRotor() {
        return this.rotor;
    }

}
