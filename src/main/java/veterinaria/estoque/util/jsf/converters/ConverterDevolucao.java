package veterinaria.estoque.util.jsf.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.service.ServiceDevolucao;

@RequestScoped
@FacesConverter(forClass = Devolucao.class)
public class ConverterDevolucao implements Converter {
	
	@Inject
	private ServiceDevolucao serviceDevolucao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		return serviceDevolucao.buscarPorId(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		Devolucao devolucao = (Devolucao) value;
		
		if (devolucao.getId() == null) {
			return null;
		}
		
		return devolucao.getId().toString();
	}

}
