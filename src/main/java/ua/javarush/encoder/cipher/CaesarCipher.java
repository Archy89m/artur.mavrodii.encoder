package ua.javarush.encoder.cipher;

import ua.javarush.encoder.alphabet.Alphabet;
import ua.javarush.encoder.setting.Command;
import ua.javarush.encoder.setting.Config;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CaesarCipher {

    public List<String> cryptoOperate(List<String> fromList, Config config) {

        List<String> toList = new ArrayList<>();
        List<Character> listAlphabet = config.getAlphabet().getListAlphabet();

        for (String line : fromList) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {

                boolean upperCase = Character.isUpperCase(line.charAt(i));

                char currentChar;
                if (upperCase) {
                    currentChar = Character.toLowerCase(line.charAt(i));
                } else {
                    currentChar = line.charAt(i);
                }

                char cryptoChar = cryptoChange(currentChar, listAlphabet, config);

                if (upperCase)
                    cryptoChar = Character.toUpperCase(cryptoChar);

                sb.append(cryptoChar);
            }
            toList.add(sb.toString());
        }
        return toList;
    }

    public int cryptoFindKey(List<String> fromList, Config config) {

        Integer key = 0;
        for (int numberAttemptAlphabet = 0; numberAttemptAlphabet < 3; numberAttemptAlphabet++) {
            key = findKeyAttempt(fromList, config, numberAttemptAlphabet);
            if (key != null)
                break;
        }
        if (key == null)
            throw new KeyNotFoundRuntimeException("Key is not found. Please try to use bigger text");
        return key;
    }

    private char cryptoChange(char current, List<Character> listAlphabet, Config config) {

        int currentNumber = listAlphabet.indexOf(current);
        if (currentNumber == -1)
            return current;
        int cryptoNumber = getCryptoShift(config, currentNumber, listAlphabet.size());

        return listAlphabet.get(cryptoNumber);
    }

    private int getCryptoShift(Config config, int currentNumber, int lengthAlphabet) {

        int key = config.getKey();

        if (config.getCommand() == Command.ENCRYPT) {
            return getCryptoShiftKeyEncrypt(currentNumber, key, lengthAlphabet);
        } else if (config.getCommand() == Command.DECRYPT) {
            return getCryptoShiftKeyDecrypt(currentNumber, key, lengthAlphabet);
        } else {
            return currentNumber;
        }
    }

    private int getCryptoShiftKeyEncrypt(int currentNumber, int key, int lengthAlphabet) {

        if (key >= 0) {
            return getCryptoShiftEncrypt(currentNumber, key, lengthAlphabet);
        } else {
            return getCryptoShiftDecrypt(currentNumber, -key, lengthAlphabet);
        }
    }

    private int getCryptoShiftKeyDecrypt(int currentNumber, int key, int lengthAlphabet) {

        if (key >= 0) {
            return getCryptoShiftDecrypt(currentNumber, key, lengthAlphabet);
        } else {
            return getCryptoShiftEncrypt(currentNumber, -key, lengthAlphabet);
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

    private Integer findKeyAttempt(List<String> fromList, Config config, int numberAttemptAlphabet) {

        List<Character> listAlphabet = config.getAlphabet().getListAlphabet();
        List<Character> listFrequencyAlphabet = config.getAlphabet().getListFrequencyAlphabet();
        List<Character> listFrequencyFromText = getFrequencyListFromText(fromList, listAlphabet);

        boolean keyChosen = false;
        Integer foundKey = null;

        for (int numberAttemptText = 0; numberAttemptText < 3; numberAttemptText++) {

            char charFrequencyAlphabet = listFrequencyAlphabet.get(numberAttemptAlphabet);
            char charFrequencyText = listFrequencyFromText.get(numberAttemptText);

            int positionAlphabet = listAlphabet.indexOf(charFrequencyAlphabet);
            int positionText = listAlphabet.indexOf(charFrequencyText);

            foundKey = positionText - positionAlphabet;

            keyChosen = tryToChooseKey(fromList, foundKey, config.getAlphabet());
            if (keyChosen)
                break;
        }

        if (keyChosen) {
            return foundKey;
        } else {
            return null;
        }
    }

    private boolean tryToChooseKey(List<String> fromList, int keyAttempt, Alphabet alphabet) {

        Command command;
        Config configBruceForce = new Config();

        if (keyAttempt >= 0) {
            command = Command.DECRYPT;
        } else {
            command = Command.ENCRYPT;
            keyAttempt = -keyAttempt;
        }

        configBruceForce.setCommand(command);
        configBruceForce.setAlphabet(alphabet);
        configBruceForce.setKey(keyAttempt);

        List<String> cryptoTestList = cryptoOperate(fromList, configBruceForce);

        System.out.println(cryptoTestList.get(0));
        System.out.println("Does it look like correct? Please, choose, 1 - \"Yes\", any other character - \"No\"");
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();

        return result == 1;
    }

    private List<Character> getFrequencyListFromText(List<String> fromList, List<Character> listAlphabet) {

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (String line : fromList) {
            for (char c : line.toCharArray()) {
                if (!listAlphabet.contains(c))
                    continue;
                char lowerC = Character.toLowerCase(c);
                if (frequencyMap.containsKey(lowerC)) {
                    frequencyMap.put(lowerC, frequencyMap.get(lowerC) + 1);
                } else {
                    frequencyMap.put(lowerC, 1);
                }
            }
        }

        return frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}