package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.RepositoryUsuario;
import veterinaria.estoque.repository.filter.FilterUsuario;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositoryUsuario repositoryUsuario;
	
	public Usuario buscarPorId(Long id) {
		return repositoryUsuario.buscarPorId(id);
	}
	
	public Usuario salvar(Usuario usuario) throws ManipulationException {
		try {
			return repositoryUsuario.salvar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManipulationException("Erro ao salvar usuário.");
		}
	}
	
	public List<Usuario> buscarTodos() {
		return repositoryUsuario.buscarTodos();
	}
	
	public Usuario buscarPorLogin(String login) {
		return repositoryUsuario.buscarPorLogin(login);
	}
	
	public int contarComFiltro(FilterUsuario filterUsuario) {
		return repositoryUsuario.contarComFiltro(filterUsuario);
	}
	
	public List<Usuario> filtrarPaginado(FilterUsuario filterUsuario) {
		return repositoryUsuario.filtrarPaginado(filterUsuario);
	}
	
	public boolean existePorCpf(String cpf) {
		return repositoryUsuario.existePorCpf(cpf);
	}
	
	public boolean existePorEmail(String email)  {
		return repositoryUsuario.existePorEmail(email);
	}

}
