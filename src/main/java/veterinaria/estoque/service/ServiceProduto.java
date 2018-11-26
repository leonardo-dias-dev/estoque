package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.RepositoryProduto;
import veterinaria.estoque.repository.filter.FilterProduto;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryProduto repositoryProduto;
	
	public Produto buscarPorId(Long id) {
		return repositoryProduto.buscarPorId(id);
	}
	
	public Produto salvar(Produto produto) throws ManipulationException {
		try {
			return repositoryProduto.salvar(produto);
		} catch (Exception e) {
			throw new ManipulationException("Erro ao salvar o produto.");
		}
	}
	
	public int contarComFiltro(FilterProduto filterProduto) {
		return repositoryProduto.contarComFiltro(filterProduto);
	}
	
	public List<Produto> filtrarPaginado(FilterProduto filterProduto) {
		return repositoryProduto.filtrarPaginado(filterProduto);
	}
	
	public boolean existePorNome(String nome)  {
		return repositoryProduto.existePorNome(nome);
	}
	
	public List<Produto> buscarPorNome(String nome) {
		return repositoryProduto.buscarPorNome(nome);
	}

}
