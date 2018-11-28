package veterinaria.estoque.controller.perfil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.QUsuarioAutenticado;
import veterinaria.estoque.service.ServiceMeusDados;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.jsf.UtilJSF;
import veterinaria.estoque.util.spring.UtilSpringSecurity;

@Named
@ViewScoped
public class ControllerMeusDados implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceMeusDados serviceMeusDados;
	
	@Inject
	@QUsuarioAutenticado
	private Usuario usuario;
	
	@Inject
	private UtilSpringSecurity utilSpringSecurity;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void alterar() {
		try {
			usuario = serviceMeusDados.alterar(usuario);
			
			utilSpringSecurity.atualizarUsuarioNaSessao(usuario);
			
			UtilJSF.addInfoMessage("Dados alterados com sucesso!");
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
