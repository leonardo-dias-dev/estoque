package veterinaria.estoque.controller.devolucao;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterDevolucao;
import veterinaria.estoque.service.ServiceConsultaDevolucao;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerConsultaDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceConsultaDevolucao serviceConsultaDevolucao;
	
	@Inject
	private FilterDevolucao filterDevolucao;

	@PostConstruct
	public void init() {
		
	}
	
	public void alterar(Devolucao devolucao) {
		try {
			String url = String.format("devolucao/cadastra-devolucao.xhtml?sid=%s", devolucao.getId());
			
			Faces.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage("Erro ao redirecionar para tela de alteção de saída.");
		}
	}
	
	public List<Usuario> completeUsuario(String query) {
		return serviceConsultaDevolucao.completeUsuario(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceConsultaDevolucao.completeProduto(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceConsultaDevolucao.completeLote(query);
	}

	public FilterDevolucao getFilterDevolucao() {
		return filterDevolucao;
	}

}
