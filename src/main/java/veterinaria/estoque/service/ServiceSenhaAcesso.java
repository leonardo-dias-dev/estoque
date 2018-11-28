package veterinaria.estoque.service;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.QUsuarioAutenticado;
import veterinaria.estoque.util.Criptografia;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;

@Named
@ViewScoped
public class ServiceSenhaAcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUsuario serviceUsuario;
	
	@Inject
	@QUsuarioAutenticado
	private Usuario usuario;
	
	@Transactional
	public void alterar(String novaSenha) throws ManipulationException {
		String senhaCriptografada = Criptografia.criptografiaSHA256(novaSenha);
		
		usuario.setSenha(senhaCriptografada);
		
		serviceUsuario.salvar(usuario);
	}
	
	public void validarSenhaAtual(String senhaAtual) throws ManipulationException, NegocioException {
		String senhaCriptografada = Criptografia.criptografiaSHA256(senhaAtual);
		
		if (!usuario.getSenha().equals(senhaCriptografada)) {
			throw new NegocioException("Senha autal não confere.");
		}
	}

}
