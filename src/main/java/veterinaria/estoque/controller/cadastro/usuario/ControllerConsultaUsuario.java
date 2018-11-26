package veterinaria.estoque.controller.cadastro.usuario;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterUsuario;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerConsultaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilterUsuario filterUsuario;
	
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void alterar() {
		try {
			String url = String.format("cadastro/usuario/cadastra-usuario.xhtml?sid=%s", usuario.getId());
			
			Faces.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage("Erro ao redirecionar para tela de alteção de usuário.");
		}
	}

	public FilterUsuario getFilterUsuario() {
		return filterUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
