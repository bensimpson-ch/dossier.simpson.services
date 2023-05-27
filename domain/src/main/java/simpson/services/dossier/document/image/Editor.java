package simpson.services.dossier.document.image;

import simpson.services.dossier.document.Content;

public interface Editor {

    Content rotate90Left(Content content);

    Content rotate90Right(Content content);
}
