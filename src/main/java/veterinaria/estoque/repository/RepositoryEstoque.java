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

import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.repository.filter.FilterEstoque;

@Dependent
public class RepositoryEstoque extends AbstractRepository<Estoque, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Estoque buscarPorProdutoELote(Produto produto, Lote lote) {
		TypedQuery<Estoque> typedQuery = getEntityManager().createQuery("FROM Estoque WHERE produto = :produto AND lote = :lote", Estoque.class);
		
		typedQuery.setParameter("produto", produto);
		typedQuery.setParameter("lote", lote);
		
		return typedQuery.getSingleResult();
	}
	
	public Integer contrarQuantidadePorProduto(Produto produto) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
		Root<Estoque> root = criteriaQuery.from(Estoque.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("produto"), produto);
		
		criteriaQuery.select(criteriaBuilder.sum(root.get("quantidade"))).where(predicate);
		
		TypedQuery<Integer> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		return typedQuery.getSingleResult();
	}

	public int contarComFiltro(FilterEstoque filterEstoque) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Estoque> root = criteriaQuery.from(Estoque.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterEstoque);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(condicoesComoArray);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue();
	}

	public List<Estoque> filtrarPaginado(FilterEstoque filterEstoque) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Estoque> criteriaQuery = criteriaBuilder.createQuery(Estoque.class);
		Root<Estoque> root = criteriaQuery.from(Estoque.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterEstoque);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
		
		criteriaQuery.where(todasCondicoes);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("produto").get("nome")));
		
		TypedQuery<Estoque> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(filterEstoque.getPageSize());
		typedQuery.setFirstResult(filterEstoque.getFirst());
		
		return typedQuery.getResultList();
	}
	
	private List<Predicate> getCondicoes(Root<Estoque> root, CriteriaBuilder criteriaBuilder, FilterEstoque filterEstoque) {
		List<Predicate> listaPredicate = new ArrayList<>();
		
		if (filterEstoque.getProduto() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("produto"), filterEstoque.getProduto());
			
			listaPredicate.add(predicate);
		}
		
		return listaPredicate;
	}

}
