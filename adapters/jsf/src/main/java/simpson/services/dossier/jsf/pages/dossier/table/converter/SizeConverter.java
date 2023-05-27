package simpson.services.dossier.jsf.pages.dossier.table.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import simpson.services.dossier.document.Size;

import java.text.DecimalFormat;

@FacesConverter("simpson.services.dossier.jsf.SizeConverter")
public class SizeConverter implements Converter<Size> {

    @Override
    public Size getAsObject(FacesContext context, UIComponent component, String value) {
        return new Size(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Size size) {
        long value = size.value();
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(value) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(value / Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }
}
