package simpson.services.dossier.document.pdf;

import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.Keyword;

import java.util.List;

public interface Reader {

    List<String> lines(Document document);

    List<Keyword> keywords(Document document);
}
