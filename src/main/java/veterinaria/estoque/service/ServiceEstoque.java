package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.RepositoryEstoque;
import veterinaria.estoque.repository.filter.FilterEstoque;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryEstoque repositoryEstoque;
	
	public Estoque salvar(Estoque estoque) throws ManipulationException {
		try {
			return repositoryEstoque.salvar(estoque);
		} catch (Exception e) {
			throw new ManipulationException("Erro ao salvar o estoque");
		}
	}
	
	public Estoque buscarPorProdutoELote(Produto produto, Lote lote) {
		return repositoryEstoque.buscarPorProdutoELote(produto, lote);
	}

	public int contarComFiltro(FilterEstoque filterEstoque) {
		return repositoryEstoque.contarComFiltro(filterEstoque);
	}

	public List<Estoque> filtrarPaginado(FilterEstoque filterEstoque) {
		return repositoryEstoque.filtrarPaginado(filterEstoque);
	}
	
	public Integer contrarQuantidadePorProduto(Produto produto) {
		Integer quantidade = repositoryEstoque.contrarQuantidadePorProduto(produto);
		
		return quantidade == null ? 0 : quantidade;
	}

}
