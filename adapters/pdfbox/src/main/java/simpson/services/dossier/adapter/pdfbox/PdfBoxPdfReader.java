package simpson.services.dossier.adapter.pdfbox;

import jakarta.enterprise.context.Dependent;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.language.detect.LanguageDetector;
import simpson.services.dossier.document.Content;
import simpson.services.dossier.document.ExtractedMetaData;
import simpson.services.dossier.document.Keyword;
import simpson.services.dossier.document.pdf.PdfReader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Dependent
public class PdfBoxPdfReader implements PdfReader {

    private LanguageDetector languageDetector;

    @Override
    public List<String> lines(Content content) {
        var bytes = content.bytes();
        try (var pdf = Loader.loadPDF(bytes)) {
            var pdfTextStripper = new PDFTextStripper();
            var text = pdfTextStripper.getText(pdf);
            System.out.println("text = " + text);

        } catch (IOException e) {
            return List.of();
        }
        return null;
    }

    @Override
    public List<Keyword> keywords(Content content) {
        var bytes = content.bytes();
        try (var pdf = Loader.loadPDF(bytes)) {
            var pdfTextStripper = new PDFTextStripper();
            var text = pdfTextStripper.getText(pdf).replace('\n', ' ');
            return new KeywordParser().keywords(text);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExtractedMetaData extractMetaData(Content content) {
        try (var pdf = Loader.loadPDF(content.bytes())) {
            var documentInformation = pdf.getDocumentInformation();
            return new ExtractedMetaData(pdf.getNumberOfPages(), Collections.emptyMap(), "title", null, documentInformation.getAuthor(), documentInformation.getKeywords(), LocalDateTime.ofInstant(documentInformation.getCreationDate().toInstant(), documentInformation.getCreationDate().getTimeZone().toZoneId()), null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
