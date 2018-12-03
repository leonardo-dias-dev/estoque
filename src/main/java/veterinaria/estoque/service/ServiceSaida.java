package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Saida;
import veterinaria.estoque.repository.RepositorySaida;
import veterinaria.estoque.repository.filter.FilterSaida;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceSaida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositorySaida repositorySaida;
	
	public Saida salvar(Saida saida) throws ManipulationException {
		try {
			return repositorySaida.salvar(saida);
		} catch (Exception e) {
			throw new ManipulationException("Erro ao salvar saída.");
		}
	}
	
	public int contarComFiltro(FilterSaida filterSaida) {
		return repositorySaida.contarComFiltro(filterSaida);
	}
	
	public List<Saida> filtrarPaginado(FilterSaida filterSaida) {
		return repositorySaida.filtrarPaginado(filterSaida);
	}

	public Saida buscarPorId(Long id) {
		return repositorySaida.buscarPorId(id);
	}

}
