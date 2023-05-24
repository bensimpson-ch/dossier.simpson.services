package simpson.services.dossier.adapter.persistence;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Converter(autoApply = true)
public class MimeTypeConverter implements AttributeConverter<MimeType, String> {

    private static final MimeType UNKNOWN_MIMETYPE = new MimeType();

    @Override
    public String convertToDatabaseColumn(MimeType mimeType) {
        return Objects.nonNull(mimeType) ? mimeType.getBaseType() : null;
    }

    @Override
    public MimeType convertToEntityAttribute(String mimeType) {
        try {
            return Objects.nonNull(mimeType) ? new MimeType(mimeType) : null;
        } catch (MimeTypeParseException mimeTypeParseException) {
            Logger.getLogger(MimeTypeConverter.class.getName()).log(Level.WARNING, "unknown document mimetype {}", mimeType);
            //a document must have a valid mime type in order to be saved in the database
            return UNKNOWN_MIMETYPE;
        }
    }
}
