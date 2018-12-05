package veterinaria.estoque.controller.entrada;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.service.ServiceCadastraEntrada;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;
import veterinaria.estoque.util.jsf.UtilJSF;

@Named
@ViewScoped
public class ControllerCadastraEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceCadastraEntrada serviceCadastraEntrada;
	
	@Inject
	private Entrada entrada;
	
	private Entrada entradaAlterar;
	
	@Inject
	private Lote lote;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void prepararAlteracao() {
		if (entradaAlterar != null) {
			setEntrada(entradaAlterar);
			setLote(entradaAlterar.getLote());
		}
	}
	
	public void incluir() {
		try {
			entrada.setLote(lote);
			
			serviceCadastraEntrada.validarInclusao(entrada);
			serviceCadastraEntrada.incluir(entrada);
			
			limpar();
			
			UtilJSF.addInfoMessage("Entrada salva com sucesso!");
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		} catch (NegocioException e) {
			UtilJSF.addWarnMessage(e.getMessage());
		}
	}
	
	public void alterar() {
		try {
			entrada.setLote(lote);
			
			serviceCadastraEntrada.validarAlteracao(entrada);
			serviceCadastraEntrada.alterar(entrada);
			
			limpar();
			
			UtilJSF.addInfoMessage("Entrada salva com sucesso!");
		} catch (ManipulationException e) {
			e.printStackTrace();
			UtilJSF.addErrorMessage(e.getMessage());
		} catch (NegocioException e) {
			UtilJSF.addWarnMessage(e.getMessage());
		}
	}
	
	private void limpar() {
		entrada = new Entrada();
		lote = new Lote();
	}

	public List<Produto> completeProduto(String query) {
		return serviceCadastraEntrada.completeProduto(query);
	}

	public boolean alteracao() {
		return entradaAlterar != null;
	}
	
	public Entrada getEntrada() {
		return entrada;
	}

	public Lote getLote() {
		return lote;
	}

	public Entrada getEntradaAlterar() {
		return entradaAlterar;
	}

	public void setEntradaAlterar(Entrada entradaAlterar) {
		this.entradaAlterar = entradaAlterar;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

}
