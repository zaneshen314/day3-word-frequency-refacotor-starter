import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MSG = "Calculate Error";

    public String getWordFrequency(String inputStr) {
        if (inputStr.split(SPACE_REGEX).length == 1) {
            return inputStr + " 1";
        }
        try {
            List<WordFrequency> frequencies = getWordFrequencies(inputStr);
            return buildResult(frequencies);
        } catch (Exception e) {
            return ERROR_MSG;
        }
    }

    private List<WordFrequency> getWordFrequencies(String inputStr) {
        return Arrays.stream(inputStr.split(SPACE_REGEX))
                .collect(Collectors.groupingBy(word -> word))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .toList();
    }


    private String buildResult(List<WordFrequency> frequencies) {
        return frequencies.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + " " + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }
}
