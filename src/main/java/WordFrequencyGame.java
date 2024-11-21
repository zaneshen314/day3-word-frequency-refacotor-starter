import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MSG = "Calculate Error";
    public static final String SPACE_BREAK =  " ";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        }
        try {
            return getWordFrequencies(sentence);
        } catch (Exception e) {
            return ERROR_MSG;
        }
    }

    private String getWordFrequencies(String sentence) {
        return Arrays.stream(sentence.split(SPACE_REGEX))
                .collect(Collectors.groupingBy(word -> word))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + SPACE_BREAK + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }


}
