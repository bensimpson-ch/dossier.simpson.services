package simpson.services.dossier.jsf.pages.dossier.table.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import simpson.services.dossier.document.Modified;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@FacesConverter("simpson.services.dossier.jsf.ModifiedConverter")
public class ModifiedConverter implements Converter<Modified> {

    @Override
    public Modified getAsObject(FacesContext context, UIComponent component, String value) {
        return new Modified(LocalDateTime.now());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Modified modified) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return modified.timestamp().format(formatter);
    }
}
