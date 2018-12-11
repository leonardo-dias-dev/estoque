package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.repository.RepositoryLote;
import veterinaria.estoque.repository.filter.FilterLote;

@Dependent
public class ServiceLote implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryLote repositoryLote;
	
	public List<Lote> buscarPorNumero(String numero) {
		return repositoryLote.buscarPorNumero(numero);
	}
	
	public Lote buscarPorId(Long id) {
		return repositoryLote.buscarPorId(id);
	}

	public int contarComFiltro(FilterLote filterLote) {
		return 0;
	}

	public List<Lote> filtrarPaginado(FilterLote filterLote) {
		return null;
	}

}
