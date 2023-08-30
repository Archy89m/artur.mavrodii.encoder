package ua.javarush.encoder.alphabet;

import java.util.HashMap;
import java.util.Map;


public class AlphabetUtil {

    public Alphabet detectLanguage(String line) {

        Map<Alphabet, Integer> frequencies = new HashMap<>();

        Alphabet eng = new EnglishAlphabet();
        Alphabet ukr = new UkrainianAlphabet();

        frequencies.put(eng, 0);
        frequencies.put(ukr, 0);

        for (char c : line.toCharArray()) {
            switch (Character.toLowerCase(c)) {
                case 'e', 't', 'a', 'o', 'i' -> frequencies.put(eng, frequencies.get(eng) + 1);
                case 'о', 'а', 'н', 'и', 'т' -> frequencies.put(ukr, frequencies.get(ukr) + 1);
            }
        }

        Map.Entry<Alphabet, Integer> mostFrequent = null;
        for (Map.Entry<Alphabet, Integer> entry : frequencies.entrySet()) {
            if (mostFrequent == null || entry.getValue().compareTo(mostFrequent.getValue()) > 0)
                mostFrequent = entry;
        }
        return mostFrequent != null ? mostFrequent.getKey() : null;
    }
}
