package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import veterinaria.estoque.model.entidades.Produto;

@Dependent
public class ServiceConsultaEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	@Inject
	private ServiceEstoque serviceEstoque;
	
	public List<Produto> completeProduto(String query) {
		return serviceProduto.buscarPorNome(query);
	}
	
	public Integer contrarQuantidadePorProduto(Produto produto) {
		return serviceEstoque.contrarQuantidadePorProduto(produto);
	}

}
