package veterinaria.estoque.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterProduto;

@Dependent
public class RepositoryProduto extends AbstractRepository<Produto, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<Produto> buscarPorNome(String nome) {
		TypedQuery<Produto> typedQuery = getEntityManager().createQuery("FROM Produto WHERE LOWER(nome) LIKE LOWER(:nome)", Produto.class);
		
		typedQuery.setParameter("nome", "%" + nome + "%");
		
		return typedQuery.getResultList();
	}
	
	public boolean existePorNome(String nome)  {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(predicate);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue() > 0;
	}
	
	public int contarComFiltro(FilterProduto filterProduto) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterProduto);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(condicoesComoArray);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue();
	}
	
	public List<Produto> filtrarPaginado(FilterProduto filterProduto) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterProduto);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
		
		criteriaQuery.where(todasCondicoes);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));
		
		TypedQuery<Produto> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(filterProduto.getPageSize());
		typedQuery.setFirstResult(filterProduto.getFirst());
		
		return typedQuery.getResultList();
	}

	private List<Predicate> getCondicoes(Root<Produto> root, CriteriaBuilder criteriaBuilder, FilterProduto filterProduto) {
		List<Predicate> listaPredicate = new ArrayList<>();
		
		if (filterProduto.getNome() != null) {
			Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filterProduto.getNome() + "%");
			
			listaPredicate.add(predicate);
		}
		
		return listaPredicate;
	}
	
}
