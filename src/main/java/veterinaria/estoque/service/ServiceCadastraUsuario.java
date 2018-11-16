package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.util.Criptografia;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;

@Dependent
public class ServiceCadastraUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUsuario serviceUsuario;
	
	public void validarInclusao(Usuario usuario) throws NegocioException {
		boolean existePorCpf = serviceUsuario.existePorCpf(usuario.getCpf());
		
		if (existePorCpf) {
			throw new NegocioException("Já existe um usuário cadastrado com o mesmo CPF.");
		}
		
		boolean existeEmail = serviceUsuario.existePorEmail(usuario.getEmail());
		
		if (existeEmail) {
			throw new NegocioException("Já existe um usuário cadastrado com o mesmo e-mail.");
		}
	}
	
	public void validarAlteracao(Usuario usuarioNovo, Usuario usuarioAlterar) throws NegocioException {
		if (!usuarioNovo.getEmail().equals(usuarioAlterar.getEmail())) {
			boolean existeEmail = serviceUsuario.existePorEmail(usuarioNovo.getEmail());
			
			if (existeEmail) {
				throw new NegocioException("Já existe um usuário cadastrado com o mesmo e-mail.");
			}
		}
	}
	
	@Transactional
	public void incluir(Usuario usuario) throws ManipulationException {
		if (usuario.getSenha() != null) {
			String senha = criptografarSenha(usuario.getSenha());
			
			usuario.setSenha(senha);
		}
		
		serviceUsuario.salvar(usuario);
	}
	
	@Transactional
	public void alterar(Usuario usuarioNovo, Usuario usuarioAlterar) throws ManipulationException {
		if (usuarioNovo.getSenha() != null) {
			String senha = criptografarSenha(usuarioNovo.getSenha());
			
			usuarioNovo.setSenha(senha);
		} else {
			if (usuarioAlterar.getSenha() != null) {
				usuarioNovo.setSenha(usuarioAlterar.getSenha());
			}
		}
		
		serviceUsuario.salvar(usuarioNovo);
	}
	
	private String criptografarSenha(String senha) throws ManipulationException {
		return Criptografia.criptografiaSHA256(senha);
	}

}
