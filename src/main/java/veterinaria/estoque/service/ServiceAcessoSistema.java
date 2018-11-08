package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.util.exceptions.AcessoException;
import veterinaria.estoque.util.jsf.UtilMessageProvider;

@Dependent
public class ServiceAcessoSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void validarSenha(Usuario usuario, String senha) throws AcessoException {
		if (usuario.getSenha().equals(senha)) {
			throw new AcessoException(UtilMessageProvider.getValue("usuario-invalido", "messages-acesso-sistema"));
		}
	}
	
	public void validarUsuario(Usuario usuario) throws AcessoException {
		if (usuario == null) {
			throw new AcessoException(UtilMessageProvider.getValue("usuario-invalido", "messages-acesso-sistema"));
		}
	}

}
