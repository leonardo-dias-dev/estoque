package veterinaria.estoque.controller.cadastro.produto;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.enums.UnidadeMedida;
import veterinaria.estoque.service.ServiceCadastraProduto;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerCadastraProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceCadastraProduto serviceCadastraProduto;
	
	@Inject
	private Produto produto;
	
	private Produto produtoAlterar;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void prepararAlteracao() {
		if (produtoAlterar != null) {
			produto = produtoAlterar;
		}
	}
	
	public void incluir() {
		try {
			serviceCadastraProduto.validarInclusao(produto);
			
			serviceCadastraProduto.incluir(produto);
			
			mensagemProdutoSalvo();
			
			limpar();
		} catch (NegocioException e) {
			UtilJSF.addInfoMessage(e.getMessage());
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		}
	}
	
	public void alterar() {
		try {
			serviceCadastraProduto.validarAlteracao(produto);
			
			serviceCadastraProduto.alterar(produto);
			
			mensagemProdutoSalvo();
			
			limpar();
		} catch (NegocioException e) {
			UtilJSF.addInfoMessage(e.getMessage());
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		}
	}
	
	private void mensagemProdutoSalvo() {
		UtilJSF.addInfoMessage("Produto salvo com sucesso!");
	}
	
	private void limpar() {
		produto = new Produto();
	}
	
	public UnidadeMedida[] getListaUnidadeMedia() {
		return UnidadeMedida.values();
	}
	
	public boolean alteracao() {
		return produto != null && produto.getId() != null;
	}

	public Produto getProduto() {
		return produto;
	}

	public Produto getProdutoAlterar() {
		return produtoAlterar;
	}

	public void setProdutoAlterar(Produto produtoAlterar) {
		this.produtoAlterar = produtoAlterar;
	}

}
