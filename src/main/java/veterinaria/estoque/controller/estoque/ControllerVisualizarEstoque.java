package veterinaria.estoque.controller.estoque;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.filter.FilterEstoque;

@Named
@ViewScoped
public class ControllerVisualizarEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilterEstoque filterEstoque;
	
	private Produto produto;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void prepararVisualizacao() {
		filterEstoque.setProduto(produto);
		filterEstoque.setDataValidadeLote(new Date());
		filterEstoque.setLotesComProdutos(true);
		filterEstoque.setOrdenarPorDataValidadeLote(true);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public FilterEstoque getFilterEstoque() {
		return filterEstoque;
	}

}
