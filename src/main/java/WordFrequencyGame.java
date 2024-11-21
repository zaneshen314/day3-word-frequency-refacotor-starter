import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MSG = "Calculate Error";
    public static final String SPACE_BREAK = " ";
    public static final Integer DEFAULT_COUNT_INT = 1;

    public String getWordFrequency(String sentence) {
        try {
            String[] sentenceArray = sentence.split(SPACE_REGEX);
            return sentenceArray.length == DEFAULT_COUNT_INT ? sentence + SPACE_BREAK + DEFAULT_COUNT_INT : getWordFrequencies(sentenceArray);
        } catch (Exception e) {
            return ERROR_MSG + e.getMessage();
        }
    }

    private String getWordFrequencies(String[] SentenceArray) {
        return Arrays.stream(SentenceArray)
                .collect(Collectors.groupingBy(word -> word))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + SPACE_BREAK + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }


}
