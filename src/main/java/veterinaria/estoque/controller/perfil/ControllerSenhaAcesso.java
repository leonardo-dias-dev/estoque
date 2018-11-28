package veterinaria.estoque.controller.perfil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.service.ServiceSenhaAcesso;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;
import veterinaria.estoque.util.jsf.UtilJSF;
import veterinaria.estoque.util.jsf.UtilPrimeFaces;

@Named
@ViewScoped
public class ControllerSenhaAcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceSenhaAcesso serviceSenhaAcesso;
	
	private String senhaAtual;
	
	private String novaSenha;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void alterar() {
		try {
			serviceSenhaAcesso.validarSenhaAtual(senhaAtual);
			
			serviceSenhaAcesso.alterar(novaSenha);
			
			UtilPrimeFaces.execute("PF('dialogSucessoSalvar').show();");
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		} catch (NegocioException e) {
			UtilJSF.addWarnMessage(e.getMessage());
		}
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
