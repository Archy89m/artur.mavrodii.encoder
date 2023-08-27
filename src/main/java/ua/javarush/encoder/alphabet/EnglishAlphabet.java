package ua.javarush.encoder.alphabet;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;



public class EnglishAlphabet implements Alphabet {

    private static EnglishAlphabet INSTANCE;
    private Map<Character, Integer> map;

    private EnglishAlphabet() {
        this.map = getAlphabetMap();
    }

    public static EnglishAlphabet getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new EnglishAlphabet();
        }
        return INSTANCE;
    }

    private Map<Character, Integer> getAlphabetMap() {

        Map<Character, Integer> map = Map.ofEntries(

                entry('A', 0), entry('B', 1), entry('C', 2), entry('D', 3), entry('E', 4),
                entry('F', 5), entry('G', 6), entry('H', 7), entry('I', 8), entry('J', 9),
                entry('K', 10), entry('L', 11), entry('M', 12), entry('N', 13), entry('O', 14),
                entry('P', 15), entry('Q', 16), entry('R', 17), entry('S', 18), entry('T', 19),
                entry('U', 20), entry('V', 21), entry('W', 22), entry('X', 23), entry('Y', 24),
                entry('Z', 25),
                entry('a', 26), entry('b', 27), entry('c', 28), entry('d', 29), entry('e', 30),
                entry('f', 31), entry('g', 32), entry('h', 33), entry('i', 34), entry('j', 35),
                entry('k', 36), entry('l', 37), entry('m', 38), entry('n', 39), entry('o', 40),
                entry('p', 41), entry('q', 42), entry('r', 43), entry('s', 44), entry('t', 45),
                entry('u', 46), entry('v', 47), entry('w', 48), entry('x', 49), entry('y', 50),
                entry('z', 51));

        return map;
    }

    public Map<Character, Integer> getMap() {
        return this.map;
    }
}
