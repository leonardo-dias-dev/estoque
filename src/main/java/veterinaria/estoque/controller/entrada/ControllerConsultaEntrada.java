package veterinaria.estoque.controller.entrada;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterEntrada;
import veterinaria.estoque.service.ServiceConsultaEntrada;

@Named
@ViewScoped
public class ControllerConsultaEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaEntrada serviceConsultaEntrada;
	
	@Inject
	private FilterEntrada filterEntrada;
	
	@PostConstruct
	public void init() {
		
	}
	
	public List<Usuario> completeUsuario(String query) {
		return serviceConsultaEntrada.completeUsuario(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaEntrada.completeProduto(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceConsultaEntrada.completeLote(query);
	}

	public FilterEntrada getFilterEntrada() {
		return filterEntrada;
	}

}
