package veterinaria.estoque.util.jsf.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.service.ServiceLote;

@RequestScoped
@FacesConverter(forClass = Lote.class)
public class ConverterLote implements Converter {
	
	@Inject
	private ServiceLote serviceLote;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		return serviceLote.buscarPorId(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		Lote lote = (Lote) value;
		
		if (lote.getId() == null) {
			return null;
		}
		
		return lote.getId().toString();
	}

}
