package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.repository.RepositoryLote;

@Dependent
public class ServiceLote implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryLote repositoryLote;
	
	public List<Lote> buscarPorNumero(Long numero) {
		return repositoryLote.buscarPorNumero(numero);
	}

}
