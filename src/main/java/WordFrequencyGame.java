import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MSG = "Calculate Error";
    public static final String SPACE_BREAK = " ";

    public String getWordFrequency(String sentence) {
        try {
            return Arrays.stream(sentence.split(SPACE_REGEX))
                    .collect(Collectors.groupingBy(word -> word))
                    .entrySet().stream()
                    .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                    .sorted(Comparator.comparingInt(WordFrequency::getCount).reversed())
                    .map(w -> w.getWord() + SPACE_BREAK + w.getCount())
                    .collect(Collectors.joining(LINE_BREAK));
        } catch (Exception e) {
            return ERROR_MSG + e.getMessage();
        }
    }
}
