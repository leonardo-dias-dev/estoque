package veterinaria.estoque.util.jsf;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class UtilMessageProvider {
	
	private static ResourceBundle getBundle(String resource) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, resource);
		
		return resourceBundle;
	}
	
	/**
	 * Retorna uma mensagem definida em algum arquivo .properties
	 * 
	 * @param StringSimetrya chave do valor desejado no arquivo .properties
	 * @param StringSimetrya nome do mapeamento feito no arquivo faces-config.xml, o valor
	 * esperado nesse atributo é correspondente ao valor informado no elemento <var> presente
	 * no elemento <resource-bundle>
	 * 
	 * @return String mensagem definida para a chave informada no resource informado.
	 * */
	public static String getValue(String key, String resource) {
		try {
			ResourceBundle resourceBundle = getBundle(resource);
			String message = resourceBundle.getString(key);
			
			return message;
		} catch (MissingResourceException e) {
			return "???".concat(key).concat("??? não encontrado");
		}
	}
	
}
