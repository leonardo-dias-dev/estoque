package veterinaria.estoque.repository;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import veterinaria.estoque.model.entidades.Usuario;

@Dependent
public class RepositoryUsuario extends AbstractRepository<Usuario, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void buscarPorLogin(String login) {
		
	}

}
