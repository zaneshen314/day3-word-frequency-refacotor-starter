import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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

                List<WordFrequency> frequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    frequencies.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getWorkFrequencyMap(frequencies);

                List<WordFrequency> frequencieList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequenciesMap.entrySet()) {
                    WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
                    frequencieList.add(input);
                }
                frequencies = frequencieList;

                frequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordFrequency w : frequencies) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MSG;
            }
        }
    }

    private Map<String, List<WordFrequency>> getWorkFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordToWordFrequenciesMap.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrequencyArray = new ArrayList<>();
                wordFrequencyArray.add(wordFrequency);
                wordToWordFrequenciesMap.put(wordFrequency.getWord(), wordFrequencyArray);
            } else {
                wordToWordFrequenciesMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordToWordFrequenciesMap;
    }
}
