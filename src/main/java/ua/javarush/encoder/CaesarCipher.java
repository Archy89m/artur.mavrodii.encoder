package ua.javarush.encoder;

import ua.javarush.encoder.alphabet.EnglishAlphabet;
import ua.javarush.encoder.application.Application;
import ua.javarush.encoder.application.Command;

import java.util.*;
import java.util.stream.Collectors;

public class CaesarCipher {

    public List<String> cryptoOperation(List<String> fromList, Application crypto) {

        List<String> toList = new ArrayList<>();

        List<Character> listAlphabet = crypto.getAlphabet().getListAlphabet();
        for (String line : fromList) {
            String cryptoLine = "";
            for (int i = 0; i < line.length(); i++) {

                Boolean upperCase = Character.isUpperCase(line.charAt(i));

                char currentChar;
                if (upperCase) {
                    currentChar = Character.toLowerCase(line.charAt(i));
                } else {
                    currentChar = line.charAt(i);
                }

                char cryptoChar = cryptoChange(currentChar, listAlphabet, crypto);

                if (upperCase)
                    cryptoChar = Character.toUpperCase(cryptoChar);

                cryptoLine = cryptoLine + cryptoChar;
            }
            toList.add(cryptoLine);
        }
        return toList;
    }

    public int cryptoFindKey(List<String> fromList, Application crypto) {

        Integer key = 0;
        for (int numberAttemptAlphabet = 0; numberAttemptAlphabet < 3; numberAttemptAlphabet++) {
            key = findKeyAttempt(fromList, crypto, numberAttemptAlphabet);
            if (key != null)
                break;
        }
        if (key == null)
            throw new RuntimeException("Key is not found. Please try to use bigger text");
        return key;
    }

    private char cryptoChange(char current, List<Character> listAlphabet, Application crypto) {

        Integer currentNumber = listAlphabet.indexOf(current);
        if (currentNumber == -1)
            return current;
        int cryptoNumber = getCryptoShift(crypto, currentNumber, listAlphabet.size());

        return listAlphabet.get(cryptoNumber);
    }

    private int getCryptoShift(Application crypto, int currentNumber, int lengthAlphabet) {

        int key = crypto.getKey();

        if (crypto.getCommand() == Command.ENCRYPT) {
            if (key >= 0) {
                return getCryptoShiftEncrypt(currentNumber, key, lengthAlphabet);
            } else {
                return getCryptoShiftDecrypt(currentNumber, -key, lengthAlphabet);
            }
        } else if (crypto.getCommand() == Command.DECRYPT) {
            if (key >= 0) {
                return getCryptoShiftDecrypt(currentNumber, key, lengthAlphabet);
            } else {
                return getCryptoShiftEncrypt(currentNumber, -key, lengthAlphabet);
            }
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
            if (temp <= lengthAlphabet) {
                return lengthAlphabet - temp;
            } else {
                int rest = temp % lengthAlphabet;
                return lengthAlphabet - rest;
            }
        } else {
            return currentNumber - key;
        }
    }

    private Integer findKeyAttempt(List<String> fromList, Application crypto, int numberAttemptAlphabet) {

        List<Character> listAlphabet = crypto.getAlphabet().getListAlphabet();
        List<Character> listFrequencyAlphabet = crypto.getAlphabet().getListFrequencyAlphabet();
        List<Character> listFrequencyFromText = getFrequencyListFromText(fromList, listAlphabet);

        boolean keyChosen = false;
        Integer foundKey = null;

        for (int numberAttemptText = 0; numberAttemptText < 3; numberAttemptText++) {

            char charFrequencyAlphabet = listFrequencyAlphabet.get(numberAttemptAlphabet);
            char charFrequencyText = listFrequencyFromText.get(numberAttemptText);

            int positionAlphabet = listAlphabet.indexOf(charFrequencyAlphabet);
            int positionText = listAlphabet.indexOf(charFrequencyText);

            foundKey = Integer.valueOf(positionText - positionAlphabet);

            keyChosen = tryToChooseKey(fromList, foundKey);
            if (keyChosen)
                break;
        }

        if (keyChosen) {
            return foundKey;
        } else {
            return null;
        }
    }

    private boolean tryToChooseKey(List<String> fromList, int keyAttempt) {

        Command command;

        Application cryptoBruceForce = new Application();

        if (keyAttempt >= 0) {
            command = Command.DECRYPT;
        } else {
            command = Command.ENCRYPT;
            keyAttempt = -keyAttempt;
        }

        cryptoBruceForce.setCommand(command);
        cryptoBruceForce.setAlphabet(EnglishAlphabet.getInstance());
        cryptoBruceForce.setKey(keyAttempt);

        List<String> cryptoTestList = cryptoOperation(fromList, cryptoBruceForce);

        System.out.println("Try to read text:");
        System.out.println(cryptoTestList.get(0).toString());
        System.out.println("Does it look like correct? Please, choose, 1 - \"Yes\", Any other character - \"No\"" );
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    private List<Character> getFrequencyListFromText(List<String> fromList, List<Character> listAlphabet) {

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (String line : fromList) {
            for (char c : line.toCharArray()) {
                if (listAlphabet.indexOf(c) == -1)
                    continue;
                char lowerC = Character.toLowerCase(c);
                if (frequencyMap.containsKey(lowerC)) {
                    frequencyMap.put(lowerC, frequencyMap.get(lowerC) + 1);
                } else {
                    frequencyMap.put(lowerC, 1);
                }
            }
        }

        List<Character> FrequencyListFromText = frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return FrequencyListFromText;
    }
}
