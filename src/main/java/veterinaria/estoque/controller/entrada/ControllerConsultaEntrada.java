package veterinaria.estoque.controller.entrada;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterEntrada;
import veterinaria.estoque.service.ServiceConsultaEntrada;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerConsultaEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaEntrada serviceConsultaEntrada;
	
	@Inject
	private FilterEntrada filterEntrada;
	
	private Entrada entrada;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void alterar() {
		try {
			String url = String.format("entrada/cadastra-entrada.xhtml?sid=%s", entrada.getId());
			
			Faces.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage("Erro ao redirecionar para tela de alteção de produto.");
		}
	}
	
	public List<Usuario> completeUsuario(String query) {
		return serviceConsultaEntrada.completeUsuario(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaEntrada.completeProduto(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceConsultaEntrada.completeLote(query);
	}

	public FilterEntrada getFilterEntrada() {
		return filterEntrada;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

}
