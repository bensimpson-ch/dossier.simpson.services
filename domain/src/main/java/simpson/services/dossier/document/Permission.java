package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;

@ValueObject
public enum Permission {

    NONE,

    READ,

    MODIFY,

    DELETE

}
