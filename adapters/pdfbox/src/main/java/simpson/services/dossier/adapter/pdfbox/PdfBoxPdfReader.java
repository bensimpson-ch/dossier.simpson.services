package simpson.services.dossier.adapter.pdfbox;

import jakarta.enterprise.context.Dependent;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.language.detect.LanguageDetector;
import simpson.services.dossier.document.Content;
import simpson.services.dossier.document.Keyword;
import simpson.services.dossier.document.pdf.PdfReader;

import java.io.IOException;
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
            throw new RuntimeException(e);
        }
    }
}
