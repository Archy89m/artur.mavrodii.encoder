package ua.javarush.encoder;

import ua.javarush.encoder.application.Application;
import ua.javarush.encoder.application.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CaesarCipher {

    public List<String> cryptoOperation(List<String> fromList, Application crypto) {

        Map<Character, Integer> mapAlphabet = crypto.getAlphabet().getMap();
        List<String> toList = new ArrayList<>();

        for (String line: fromList) {
            String cryptoLine = "";
            for (int i = 0; i < line.length(); i++) {
                char cryptoChar = cryptoChange(line.charAt(i), mapAlphabet, crypto);
                cryptoLine = cryptoLine + cryptoChar;
            }
            toList.add(cryptoLine);
        }
        return toList;
    }

    private char cryptoChange(char current, Map<Character, Integer> mapAlphabet, Application crypto) {

        Integer currentNumber = mapAlphabet.get(current);
        if (currentNumber == null)
            return current;
        int cryptoNumber = getCryptoShift(crypto, currentNumber, mapAlphabet.size());

        Optional<Character> cryptoValue = mapAlphabet.entrySet()
                                                     .stream()
                                                     .filter(entry -> entry.getValue() == cryptoNumber)
                                                     .map(Map.Entry::getKey)
                                                     .findFirst();
        return cryptoValue.get();
    }

    private int getCryptoShift(Application crypto, int currentNumber, int lengthAlphabet) {

        int key = crypto.getKey();

        if (crypto.getCommand() == Command.ENCRYPT) {
            return getCryptoShiftEncrypt(currentNumber, key, lengthAlphabet);
        } else if (crypto.getCommand() == Command.DECRYPT) {
            return getCryptoShiftDecrypt(currentNumber, key, lengthAlphabet);
        } else {
            return currentNumber;
        }
    }

    private int getCryptoShiftEncrypt(int currentNumber, int key, int lengthAlphabet) {

        if (currentNumber + key >= lengthAlphabet) {
            return (currentNumber + key) % lengthAlphabet;
        } else {
            return currentNumber + key;
        }
    }

    private int getCryptoShiftDecrypt(int currentNumber, int key, int lengthAlphabet) {

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
    }
}
