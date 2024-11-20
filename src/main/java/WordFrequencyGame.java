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
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(SPACE_REGEX);

                List<WordFrequency> frequencies = getInitialWordFrequencies(words);

                //get the map for the next step of sizing the same word
                frequencies = getWordFrequencies(frequencies);

                return buildResult(frequencies);
            } catch (Exception e) {
                return ERROR_MSG;
            }
        }
    }

    private static List<WordFrequency> getInitialWordFrequencies(String[] words) {
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private static List<WordFrequency> getWordFrequencies(List<WordFrequency> frequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getWorkFrequencyMap(frequencies);

        return wordToWordFrequenciesMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .toList();
    }

    private static String buildResult(List<WordFrequency> frequencies) {
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
