package ua.javarush.encoder.Alphabet;

import java.util.HashMap;
import java.util.Map;


    public class EnglishAlphabet implements Alphabet{
        private static EnglishAlphabet INSTANCE;
        private Map<Character, Integer> map;
        private EnglishAlphabet () {
            map = getMapAlphabet();
        }
        public static EnglishAlphabet getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new EnglishAlphabet();
            }
            return INSTANCE;
        }
        private static Map<Character, Integer> getMapAlphabet() {
            Map<Character, Integer> map = new HashMap<>();
            int i = 0;
            for (char ch = 'A'; ch <= 'Z'; ++ch, i++)
                map.put(ch, i);
            for (char ch = 'a'; ch <= 'z'; ++ch, i++)
                map.put(ch, i);
            return map;
        }
        public Map<Character, Integer> getMap() {
            return this.map;
        }
    }
