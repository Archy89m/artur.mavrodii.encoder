package ua.javarush.encoder.alphabet;

import java.util.Arrays;
import java.util.List;


public class EnglishAlphabet implements Alphabet {

    private final List<Character> listAlphabet;
    private final List<Character> frequencyListAlphabet;

    public EnglishAlphabet() {

        this.listAlphabet = getAlphabetList();
        this.frequencyListAlphabet = getFrequencyListAlphabet();
    }

    private List<Character> getAlphabetList() {

        return Arrays.asList(
                'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z');
    }

    public List<Character> getListAlphabet() {
        return this.listAlphabet;
    }

    private List<Character> getFrequencyListAlphabet() {

        return Arrays.asList(
                'e','t','a','o','i','n','s','h','r','d',
                'l','c','u','m','w','f','g','y','p','b',
                'v','k','j','x','q','z');
    }

    public List<Character> getListFrequencyAlphabet() {
        return this.frequencyListAlphabet;
    }
}