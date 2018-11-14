package veterinaria.estoque.util.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import veterinaria.estoque.util.UtilString;

@FacesConverter("converterCpf")
public class ConverterCpf implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		return UtilString.retiraMascara(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		return UtilString.formataCpf(String.valueOf(value));
	}

}
