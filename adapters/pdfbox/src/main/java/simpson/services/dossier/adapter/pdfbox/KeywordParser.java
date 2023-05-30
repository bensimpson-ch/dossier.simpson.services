package simpson.services.dossier.adapter.pdfbox;

import jakarta.enterprise.context.Dependent;
import simpson.services.dossier.document.Keyword;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Dependent
public class KeywordParser {

    public List<Keyword> keywords(String text) {
        //determine which words are present
        var words = Arrays.stream(text.toLowerCase().split("\\W+")).toList();
        var wordCounts = words.stream().collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));
        return wordCounts.keySet().stream()
                .filter(key -> this.isValidKeyword(key, wordCounts))
                .map(Keyword::new).toList();
    }

    private boolean isValidKeyword(String key, Map<String, Long> wordCounts) {
        return wordCounts.get(key) > 2 && key.length() > 5;
    }
}
