package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.QUsuarioAutenticado;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;

@Dependent
public class ServiceCadastraDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceLote serviceLote;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	@Inject
	private ServiceEstoque serviceEstoque;
	
	@Inject
	private ServiceDevolucao serviceDevolucao;
	
	@Inject
	private ServiceEntrada serviceEntrada;
	
	@Inject
	@QUsuarioAutenticado
	private Usuario usuario;
	
	@Transactional
	public void incluir(Devolucao devolucao, Estoque estoque) throws ManipulationException {
		estoque.setQuantidade(estoque.getQuantidade() + devolucao.getQuantidade());
		
		devolucao.setEstoque(estoque);
		devolucao.setUsuario(usuario);
		devolucao.setDataDevolucao(new Date());
		
		serviceDevolucao.salvar(devolucao);
	}
	
	public void alterar() {
		
	}
	
	public Estoque validarInclusao(Devolucao devolucao, Produto produto, Lote lote) throws NegocioException {
		Estoque estoque = serviceEstoque.buscarPorProdutoELote(produto, lote);
		
		if (estoque == null) {
			throw new NegocioException("Não existe produto para este lote.");
		}
		
		Entrada entrada = serviceEntrada.buscarPorProdutoELote(produto, lote);
		
		if (devolucao.getQuantidade() > entrada.getQuantidade()) {
			throw new NegocioException("Devolução maior do que a quantidade de entrada para este lote.");
		}
		
		int saida = entrada.getQuantidade() - estoque.getQuantidade();
		
		if (devolucao.getQuantidade() > saida) {
			throw new NegocioException("A quantidade de devolução não está proprocional a quantidade de saída do produto.");
		}
		
		return estoque;
	}
	
	public List<Lote> completeLote(String query) {
		return serviceLote.buscarPorNumero(query);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceProduto.buscarPorNome(query);
	}
	
}
