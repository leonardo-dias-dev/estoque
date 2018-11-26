package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.repository.RepositoryEstoque;
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

}
