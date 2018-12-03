package veterinaria.estoque.controller.saida;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Saida;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterSaida;
import veterinaria.estoque.service.ServiceConsultaSaida;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerConsultaSaida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaSaida serviceConsultaSaida;

	@Inject
	private FilterSaida filterSaida;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void alterar(Saida saida) {
		try {
			String url = String.format("saida/cadastra-saida.xhtml?sid=%s", saida.getId());
			
			Faces.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage("Erro ao redirecionar para tela de alteção de saída.");
		}
	}
	
	public List<Usuario> completeUsuario(String query) {
		return serviceConsultaSaida.completeUsuario(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaSaida.completeProduto(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceConsultaSaida.completeLote(query);
	}

	public FilterSaida getFilterSaida() {
		return filterSaida;
	}

}
