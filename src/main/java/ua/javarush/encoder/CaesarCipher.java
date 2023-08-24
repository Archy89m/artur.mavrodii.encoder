package ua.javarush.encoder;

import ua.javarush.encoder.Alphabet.Alphabet;
import ua.javarush.encoder.App.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CaesarCipher {
    public static List<String> cryptoOperation(List<String> fromList, Alphabet alphabet, Command command, int key) {

        List<String> toList = new ArrayList<>();
        Map<Character, Integer> mapAlphabet = alphabet.getMap();

        for (String line: fromList) {
            String cryptoLine = "";
            for (int i = 0; i < line.length(); i++) {
                char cryptoChar = cryptoChange(line.charAt(i), command, key, mapAlphabet);
                cryptoLine = cryptoLine + cryptoChar;
            }
            toList.add(cryptoLine);
        }
        return toList;
    }
    private static char cryptoChange(char current, Command command, int key, Map<Character, Integer> mapAlphabet) {

        Integer currentNumber = mapAlphabet.get(current);
        if (currentNumber == null)
            return current;
        int cryptoNumber = getCryptoShift(command, currentNumber, key, mapAlphabet.size());

        Optional<Character> cryptoValue = mapAlphabet.entrySet()
                                                     .stream()
                                                     .filter(entry -> entry.getValue() == cryptoNumber)
                                                     .map(Map.Entry::getKey)
                                                     .findFirst();
        return cryptoValue.get();
    }
    private static int getCryptoShift(Command command, int currentNumber, int key, int lengthAlphabet) {

        if (command == Command.ENCRYPT) {
            if (currentNumber + key >= lengthAlphabet) {
                return (currentNumber + key) % lengthAlphabet;
            } else {
                return currentNumber + key;
            }
        } else if (command == Command.DECRYPT) {
            if (currentNumber - key < 0) {
                int temp = key - currentNumber;
                if (temp < lengthAlphabet) {
                    return lengthAlphabet - temp;
                } else {
                    int rest = temp % lengthAlphabet;
                    return lengthAlphabet - rest;
                }
            } else {
                return currentNumber - key;
            }
        } else {
            return currentNumber;
        }
    }
}
