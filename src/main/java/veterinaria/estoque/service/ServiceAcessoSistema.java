package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.util.exceptions.AcessoException;

@Dependent
public class ServiceAcessoSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void validarSenha(Usuario usuario, String senha) throws AcessoException {
		
	}
	
	public void validarUsuario(Usuario usuario) throws AcessoException {
		
	}

}
