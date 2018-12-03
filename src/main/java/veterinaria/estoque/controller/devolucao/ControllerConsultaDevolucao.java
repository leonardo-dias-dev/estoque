package veterinaria.estoque.controller.devolucao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterDevolucao;
import veterinaria.estoque.service.ServiceConsultaDevolucao;

@Named
@ViewScoped
public class ControllerConsultaDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaDevolucao serviceConsultaDevolucao;
	
	@Inject
	private FilterDevolucao filterDevolucao;

	@PostConstruct
	public void init() {
		
	}
	
	public List<Usuario> completeUsuario(String query) {
		return serviceConsultaDevolucao.completeUsuario(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaDevolucao.completeProduto(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceConsultaDevolucao.completeLote(query);
	}

	public FilterDevolucao getFilterDevolucao() {
		return filterDevolucao;
	}

}
