package veterinaria.estoque.util.jsf.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.service.ServiceEntrada;

@RequestScoped
@FacesConverter(forClass = Entrada.class)
public class ConverterEntrada implements Converter {
	
	@Inject
	private ServiceEntrada serviceEntrada;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		return serviceEntrada.buscarPorId(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		Entrada entrada = (Entrada) value;
		
		if (entrada.getId() == null) {
			return null;
		}
		
		return entrada.getId().toString();
	}

}
