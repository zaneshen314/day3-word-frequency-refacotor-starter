import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MSG = "Calculate Error";

    public String getWordFrequency(String inputStr) {
        if (inputStr.split(SPACE_REGEX).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordFrequency> frequencies = getInitialWordFrequencies(inputStr);
                frequencies = getWordFrequencies(frequencies);
                return buildResult(frequencies);
            } catch (Exception e) {
                return ERROR_MSG;
            }
        }
    }

    private List<WordFrequency> getInitialWordFrequencies(String inputStr) {
        String[] words = inputStr.split(SPACE_REGEX);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> frequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getWorkFrequencyMap(frequencies);

        return wordToWordFrequenciesMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .toList();
    }

    private String buildResult(List<WordFrequency> frequencies) {
        return frequencies.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getWord() + " " + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private static Map<String, List<WordFrequency>> getWorkFrequencyMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord, Collectors.toCollection(ArrayList::new)));
    }
}
