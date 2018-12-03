package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;

@Dependent
public class ServiceConsultaDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUsuario serviceUsuario;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	@Inject
	private ServiceLote serviceLote;
	
	public List<Usuario> completeUsuario(String query) {
		return serviceUsuario.buscarPorNome(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceProduto.buscarPorNome(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceLote.buscarPorNumero(query);
	}

}
