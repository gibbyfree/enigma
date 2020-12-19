package models;

import java.util.List;
import java.util.ArrayList;

import static consts.Constants.ALPHABET;

public class Plugboard {
    private List<Integer> ins;
    private List <Integer> outs;

    public Plugboard(List<Pair> swaps) {
        this.ins = new ArrayList<Integer>();
        this.outs = new ArrayList<Integer>();
        for(Pair p : swaps) {
            this.ins.add(p.getX());
            this.outs.add(p.getY());
        }
    }

    public int translate(int i) {
        if(!ins.contains(i)) {
            // This character is unaffected by the plugboard
            return i;
        } else {
            char before = ALPHABET.get(i);
            char after = ALPHABET.get(outs.get(ins.indexOf(i)));
            System.out.println("An electric current passes through the plugboard! " + before + " becomes " + after + ".");
            return outs.get(ins.indexOf(i));
        }
    }
}
