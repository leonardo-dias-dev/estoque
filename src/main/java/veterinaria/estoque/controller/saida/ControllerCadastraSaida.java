package veterinaria.estoque.controller.saida;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Saida;
import veterinaria.estoque.service.ServiceCadastraSaida;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerCadastraSaida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceCadastraSaida serviceCadastraSaida;
	
	@Inject
	private Saida saida;
	
	@NotNull
	private Produto produto;
	
	@NotNull
	private Lote lote;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void incluir() {
		try {
			Estoque estoque = serviceCadastraSaida.validarInclusao(saida, produto, lote);
			
			serviceCadastraSaida.incluir(saida, estoque);
			
			mensagemSaidaSalvo();
			
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
	
	private void mensagemSaidaSalvo() {
		UtilJSF.addInfoMessage("Saída salva com sucesso.");
	}
	
	private void limpar() {
		setSaida(new Saida());
		setProduto(null);
		setLote(null);
	}

	public boolean alteracao() {
		return saida != null && saida.getId() != null;
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceCadastraSaida.completeProduto(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceCadastraSaida.completeLote(query);
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
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
