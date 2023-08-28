package ua.javarush.encoder.alphabet;

import java.util.Arrays;
import java.util.List;




public class EnglishAlphabet implements Alphabet {

    private static EnglishAlphabet INSTANCE;
    private List<Character> listAlphabet;
    private List<Character> frequencyListAlphabet;

    private EnglishAlphabet() {

        this.listAlphabet = getAlphabetList();
        this.frequencyListAlphabet = getFrequencyListAlphabet();
    }

    public static EnglishAlphabet getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new EnglishAlphabet();
        }
        return INSTANCE;
    }

    private List<Character> getAlphabetList() {

        List<Character> listAlphabet = Arrays.asList(
                'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z');
        return listAlphabet;
    }

    public List<Character> getListAlphabet() {
        return this.listAlphabet;
    }

    private List<Character> getFrequencyListAlphabet() {

        List<Character> frequencyListAlphabet = Arrays.asList(
                'e','t','a','o','i','n','s','h','r','d',
                'l','c','u','m','w','f','g','y','p','b',
                'v','k','j','x','q','z');
        return frequencyListAlphabet;
    }

    public List<Character> getListFrequencyAlphabet() {
        return this.frequencyListAlphabet;
    }
}
