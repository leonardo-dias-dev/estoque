package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Saida;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.QUsuarioAutenticado;
import veterinaria.estoque.util.exceptions.ManipulationException;
import veterinaria.estoque.util.exceptions.NegocioException;

@Dependent
public class ServiceCadastraSaida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	@Inject
	private ServiceLote serviceLote;
	
	@Inject
	private ServiceSaida serviceSaida;
	
	@Inject
	private ServiceEstoque serviceEstoque;
	
	@Inject
	@QUsuarioAutenticado
	private Usuario usuario;
	
	@Transactional
	public void incluir(Saida saida, Estoque estoque) throws ManipulationException {
		estoque.setQuantidade(estoque.getQuantidade() - saida.getQuantidade());
		
		saida.setEstoque(estoque);
		saida.setUsuario(usuario);
		saida.setDataSaida(new Date());
		
		serviceSaida.salvar(saida);
	}
	
	public Estoque validarInclusao(Saida saida, Produto produto, Lote lote) throws NegocioException {
		Estoque estoque = serviceEstoque.buscarPorProdutoELote(produto, lote);
		
		if (estoque == null) {
			throw new NegocioException("Não existe produto disponível para este lote.");
		}
		
		if (estoque.getQuantidade() < saida.getQuantidade()) {
			throw new NegocioException("Retirada maior do que a disponível para este lote.");
		}
		
		return estoque;
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceProduto.buscarPorNome(query);
	}
	
	public List<Lote> completeLote(String query) {
		return serviceLote.buscarPorNumero(query);
	}

}
