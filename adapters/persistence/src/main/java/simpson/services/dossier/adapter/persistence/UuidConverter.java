package simpson.services.dossier.adapter.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.UUID;

@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return Objects.nonNull(uuid) ? uuid.toString() : null;
    }

    @Override
    public UUID convertToEntityAttribute(String uuidString) {
        return Objects.nonNull(uuidString) ? UUID.fromString(uuidString) : null;
    }
}
