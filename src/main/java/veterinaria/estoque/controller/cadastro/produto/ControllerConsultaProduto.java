package veterinaria.estoque.controller.cadastro.produto;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.filter.FilterProduto;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerConsultaProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilterProduto filterProduto;
	
	private Produto produto;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void alterar() {
		try {
			String url = String.format("cadastro/produto/cadastra-produto.xhtml?sid=%s", produto.getId());
			
			Faces.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage("Erro ao redirecionar para tela de alteção de produto.");
		}
	}

	public FilterProduto getFilterProduto() {
		return filterProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
