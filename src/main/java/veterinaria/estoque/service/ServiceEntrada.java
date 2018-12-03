package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.RepositoryEntrada;
import veterinaria.estoque.repository.filter.FilterEntrada;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryEntrada repositoryEntrada;
	
	public Entrada buscarPorId(Long id) {
		return repositoryEntrada.buscarPorId(id);
	}
	
	public Entrada salvar(Entrada entrada) throws ManipulationException {
		try {
			return repositoryEntrada.salvar(entrada);
		} catch (Exception e) {
			throw new ManipulationException("Erro ao salvar a entrada.");
		}
	}
	
	public int contarComFiltro(FilterEntrada filterEntrada) {
		return repositoryEntrada.contarComFiltro(filterEntrada);
	}
	
	public List<Entrada> filtrarPaginado(FilterEntrada filterEntrada) {
		return repositoryEntrada.filtrarPaginado(filterEntrada);
	}
	
	public List<Entrada> bucarPorLote(Lote lote) {
		return repositoryEntrada.buscarPorLote(lote);
	}
	
	public Entrada buscarPorProdutoELote(Produto produto, Lote lote) {
		return repositoryEntrada.buscarPorProdutoELote(produto, lote);
	}

}
