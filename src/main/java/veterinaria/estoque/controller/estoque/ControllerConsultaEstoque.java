package veterinaria.estoque.controller.estoque;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.filter.FilterProduto;
import veterinaria.estoque.service.ServiceConsultaEstoque;

@Named
@ViewScoped
public class ControllerConsultaEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaEstoque serviceConsultaEstoque;
	
	@Inject
	private FilterProduto filterProduto;
	
	@PostConstruct
	public void init() {
		
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaEstoque.completeProduto(query);
	}
	
	public int carregarQuantidadeEstque(Produto produto) {
		int quantidade = serviceConsultaEstoque.contrarQuantidadePorProduto(produto);
		
		return quantidade;
	}

	public FilterProduto getFilterProduto() {
		return filterProduto;
	}

	public void setFilterProduto(FilterProduto filterProduto) {
		this.filterProduto = filterProduto;
	}

}
