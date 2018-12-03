package veterinaria.estoque.controller.devolucao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.service.ServiceCadastraDevolucao;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerCadastraDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceCadastraDevolucao serviceCadastraDevolucao;
	
	@Inject
	private Devolucao devolucao;
	
	@NotNull
	private Produto produto;
	
	@NotNull
	private Lote lote;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void incluir() {
		try {
			Estoque estoque = serviceCadastraDevolucao.validarInclusao(devolucao, produto, lote);
			
			serviceCadastraDevolucao.incluir(devolucao, estoque);
			
			mostrarMensagemSucesso();
			
			limpar();
		} catch (NegocioException e) {
			UtilJSF.addWarnMessage(e.getMessage());
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		}
	}
	
	public void alterar() {
		
	}
	
	private void mostrarMensagemSucesso() {
		UtilJSF.addInfoMessage("Saída salva com sucesso.");
	}
	
	public void limpar() {
		devolucao = new Devolucao();
		setProduto(null);
		setLote(null);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceCadastraDevolucao.completeLote(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceCadastraDevolucao.completeProduto(query);
	}
	
	public boolean alteracao() {
		return devolucao != null && devolucao.getId() != null;
	}

	public Devolucao getDevolucao() {
		return devolucao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

}
