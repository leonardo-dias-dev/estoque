package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceMeusDados implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUsuario serviceUsuario;
	
	@Transactional
	public Usuario alterar(Usuario usuario) throws ManipulationException {
		return serviceUsuario.salvar(usuario);
	}

}
