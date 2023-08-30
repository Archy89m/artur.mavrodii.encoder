package ua.javarush.encoder.alphabet;

import java.util.Arrays;
import java.util.List;

public class UkrainianAlphabet implements Alphabet{

    private final List<Character> listAlphabet;
    private final List<Character> frequencyListAlphabet;

    public UkrainianAlphabet() {

        this.listAlphabet = getAlphabetList();
        this.frequencyListAlphabet = getFrequencyListAlphabet();
    }

    private List<Character> getAlphabetList() {

        return Arrays.asList(
                'а','б','в','г','ґ','д','е','є','ж','з',
                'и','і','ї','й','к','л','м','н','о','п',
                'р','с','т','у','ф','х','ц','ч','ш','щ',
                'ь','ю','я');
    }

    public List<Character> getListAlphabet() {
        return this.listAlphabet;
    }

    private List<Character> getFrequencyListAlphabet() {

        return Arrays.asList(
                'о','а','н','и','т','е','в','і','р','с',
                'к','л','у','д','м','п','я','з','ь','б',
                'г','ч','й','х','ж','ю','ш','ц','щ','є',
                'ї','ф','ґ');
    }

    public List<Character> getListFrequencyAlphabet() {
        return this.frequencyListAlphabet;
    }
}
