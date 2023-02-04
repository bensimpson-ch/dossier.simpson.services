/**
 * Copyright 2023 Ben Simpson: ben@bensimpson.ch
 */
package simpson.services.dossier.domain.document;

public record MetaData(DocumentId documentId, String name, String description, long size, long modified) {
}
