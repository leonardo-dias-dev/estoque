package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import veterinaria.estoque.model.entidades.Usuario;

@Dependent
public class ServiceUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Usuario buscarPorLogin(String login) {
		return null;
	}

}
