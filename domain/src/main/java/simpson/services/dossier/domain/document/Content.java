/**
 * Copyright 2023 Ben Simpson: ben@bensimpson.ch
 */
package simpson.services.dossier.domain.document;

public record Content(byte[] bytes, MimeType mimeType) {
}
