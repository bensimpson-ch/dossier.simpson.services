/**
 * Copyright 2023 Ben Simpson: ben@bensimpson.ch
 */
package simpson.services.dossier.domain.document;

public record Document(DocumentId documentId, Content content, MetaData metaData) {
}
