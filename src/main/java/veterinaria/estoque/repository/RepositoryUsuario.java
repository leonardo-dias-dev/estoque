package veterinaria.estoque.repository;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;

import veterinaria.estoque.model.entidades.Usuario;

@Dependent
public class RepositoryUsuario extends AbstractRepository<Usuario, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Usuario buscarPorLogin(String login) {
		TypedQuery<Usuario> typedQuery = getEntityManager().createQuery("FROM Usuario WHERE login = :login", Usuario.class);
		
		typedQuery.setParameter("login", login);
		
		return typedQuery.getSingleResult();
	}

}
