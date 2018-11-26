package veterinaria.estoque.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.QUsuarioAutenticado;
import veterinaria.estoque.util.exceptions.ManipulationException;

@Dependent
public class ServiceCadastraEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@QUsuarioAutenticado
	private Usuario usuario;
	
	@Inject
	private ServiceEntrada serviceEntrada;
	
	@Inject
	private ServiceEstoque serviceEstoque;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	@Transactional
	public void incluir(Entrada entrada) throws ManipulationException {
		Estoque estoque = new Estoque();
		
		entrada.setUsuario(usuario);
		entrada.setDataEntrada(new Date());
		
		entrada = serviceEntrada.salvar(entrada);
		
		estoque.setLote(entrada.getLote());
		estoque.setProduto(entrada.getProduto());
		estoque.setQuantidade(entrada.getQuantidade());
		
		serviceEstoque.salvar(estoque);
	}
	
	public List<Produto> completeProduto(String query) {
		return serviceProduto.buscarPorNome(query);
	}

}
