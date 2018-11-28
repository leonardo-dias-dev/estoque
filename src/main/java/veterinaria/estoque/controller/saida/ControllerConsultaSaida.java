package veterinaria.estoque.controller.saida;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterSaida;
import veterinaria.estoque.service.ServiceConsultaSaida;

@Named
@ViewScoped
public class ControllerConsultaSaida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaSaida serviceConsultaSaida;

	@Inject
	private FilterSaida filterSaida;
	
	@PostConstruct
	public void init() {
		
	}
	
	public List<Usuario> completeUsuario(String query) {
		return serviceConsultaSaida.completeUsuario(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaSaida.completeProduto(query);
	}

	public FilterSaida getFilterSaida() {
		return filterSaida;
	}

}
