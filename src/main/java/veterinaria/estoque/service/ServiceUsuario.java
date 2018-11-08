package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.RepositoryUsuario;

@Dependent
public class ServiceUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryUsuario repositoryUsuario;
	
	public Usuario buscarPorLogin(String login) {
		return repositoryUsuario.buscarPorLogin(login);
	}

}
