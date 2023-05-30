package simpson.services.dossier.adapter.pdfbox;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class StopwordsLoader {

    public String loadStopwords(String language) {
        try (InputStream inputStream = StopwordsLoader.class.getResourceAsStream("/stopwords/en.txt")) {
            Objects.nonNull(inputStream);
            return IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }
}
