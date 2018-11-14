package veterinaria.estoque.util.jsf.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.service.ServiceUsuario;

@RequestScoped
@FacesConverter(forClass = Usuario.class)
public class ConverterUsuario implements Converter {
	
	@Inject
	private ServiceUsuario serviceUsuario;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		return serviceUsuario.buscarPorId(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		Usuario usuario = (Usuario) value;
		
		if (usuario.getId() == null) {
			return null;
		}
		
		return usuario.getId().toString();
	}

}
