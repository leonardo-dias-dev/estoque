package veterinaria.estoque.util.jsf.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.service.ServiceProduto;

@RequestScoped
@FacesConverter(forClass=Produto.class)
public class ConverterProduto implements Converter {
	
	@Inject
	private ServiceProduto serviceProduto;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		return serviceProduto.buscarPorId(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		Produto produto = (Produto) value;
		
		if (produto.getId() == null) {
			return null;
		}
		
		return produto.getId().toString();
	}

}
