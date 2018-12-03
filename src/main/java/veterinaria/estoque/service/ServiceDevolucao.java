package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.repository.RepositoryDevolucao;
import veterinaria.estoque.repository.filter.FilterDevolucao;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryDevolucao repositoryDevolucao;
	
	public Devolucao salvar(Devolucao devolucao) throws ManipulationException {
		try {
			return repositoryDevolucao.salvar(devolucao);
		} catch (Exception e) {
			throw new ManipulationException("Erro ao salvar devolução.");
		}
	}
	
	public int contarComFiltro(FilterDevolucao filterDevolucao) {
		return repositoryDevolucao.contarComFiltro(filterDevolucao);
	}
	
	public List<Devolucao> filtrarPaginado(FilterDevolucao filterDevolucao) {
		return repositoryDevolucao.filtrarPaginado(filterDevolucao);
	}

	public Devolucao buscarPorId(Long id) {
		return repositoryDevolucao.buscarPorId(id);
	}

}
