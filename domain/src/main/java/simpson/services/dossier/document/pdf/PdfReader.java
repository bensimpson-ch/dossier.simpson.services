package simpson.services.dossier.document.pdf;

import simpson.services.dossier.document.Content;
import simpson.services.dossier.document.Keyword;

import java.util.List;

public interface PdfReader {

    List<String> lines(Content content);

    List<Keyword> keywords(Content content);
}
