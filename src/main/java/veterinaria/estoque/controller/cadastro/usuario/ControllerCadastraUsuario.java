package veterinaria.estoque.controller.cadastro.usuario;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.service.ServiceCadastraUsuario;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;
import veterinaria.estoque.util.jsf.UtilJSF;
import veterinaria.estoque.util.jsf.UtilPrimeFaces;

@Named
@ViewScoped
public class ControllerCadastraUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceCadastraUsuario serviceCadastraUsuario;
	
	@Inject
	private Usuario usuario;
	
	private Usuario usuarioAlterar;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void prepararAlteracao() {
		if (usuarioAlterar != null) {
			usuario = usuarioAlterar;
		}
	}
	
	public void incluir() {
		try {
			serviceCadastraUsuario.validarUsuario(usuario);
			
			serviceCadastraUsuario.incluir(usuario);
			
			UtilPrimeFaces.execute("PF('dialogSucessoSalvar').show();");
		} catch (ManipulationException e) {
			UtilJSF.addErrorMessage(e.getMessage());
		} catch (NegocioException e) {
			UtilJSF.addWarnMessage(e.getMessage());
		}
	}
	
	public void alterar() {
		
	}
	
	public void limpar() {
		
	}
	
	public void configurarLogin() {
		usuario.setLogin(usuario.getCpf());
	}
	
	public boolean alteracao() {
		return usuario != null && usuario.getId() != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getUsuarioAlterar() {
		return usuarioAlterar;
	}

	public void setUsuarioAlterar(Usuario usuarioAlterar) {
		this.usuarioAlterar = usuarioAlterar;
	}

}
