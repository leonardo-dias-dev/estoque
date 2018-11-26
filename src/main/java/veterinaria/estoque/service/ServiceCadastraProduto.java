package veterinaria.estoque.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;

@Dependent
public class ServiceCadastraProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	public void validarInclusao(Produto produto) throws NegocioException {
		boolean existe = serviceProduto.existePorNome(produto.getNome());
		
		if (existe) {
			throw new NegocioException("Já existe um produto cadastrado com o mesmo nome.");
		}
	}
	
	public void validarAlteracao(Produto produto) throws NegocioException {
		boolean existe = serviceProduto.existePorNome(produto.getNome());
		
		if (existe) {
			throw new NegocioException("Já existe um produto cadastrado com o mesmo nome.");
		}
	}
	
	@Transactional
	public void incluir(Produto produto) throws ManipulationException {
		produto = serviceProduto.salvar(produto);
	}
	
	@Transactional
	public void alterar(Produto produto) throws ManipulationException {
		produto = serviceProduto.salvar(produto);
	}

}
