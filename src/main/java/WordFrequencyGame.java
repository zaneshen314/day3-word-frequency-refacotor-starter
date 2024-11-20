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

                List<WordFrequency> frequencies = Arrays.stream(words)
                        .map(word -> new WordFrequency(word, 1))
                        .toList();

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getWorkFrequencyMap(frequencies);

                List<WordFrequency> frequencieList = wordToWordFrequenciesMap.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .toList();

                frequencies = frequencieList;

                return frequencies.stream()
                        .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                        .map(w -> w.getWord() + " " + w.getWordCount())
                        .collect(Collectors.joining(LINE_BREAK));
            } catch (Exception e) {
                return ERROR_MSG;
            }
        }
    }

    private Map<String, List<WordFrequency>> getWorkFrequencyMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord, Collectors.toCollection(ArrayList::new)));
    }
}
