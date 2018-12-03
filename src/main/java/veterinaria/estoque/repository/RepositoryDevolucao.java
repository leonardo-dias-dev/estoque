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

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.repository.filter.FilterDevolucao;

@Dependent
public class RepositoryDevolucao extends AbstractRepository<Devolucao, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int contarComFiltro(FilterDevolucao filterDevolucao) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Devolucao> root = criteriaQuery.from(Devolucao.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterDevolucao);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(condicoesComoArray);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue();
	}
	
	public List<Devolucao> filtrarPaginado(FilterDevolucao filterDevolucao) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Devolucao> criteriaQuery = criteriaBuilder.createQuery(Devolucao.class);
		Root<Devolucao> root = criteriaQuery.from(Devolucao.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterDevolucao);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
		
		criteriaQuery.where(todasCondicoes);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataDevolucao")));
		
		TypedQuery<Devolucao> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(filterDevolucao.getPageSize());
		typedQuery.setFirstResult(filterDevolucao.getFirst());
		
		return typedQuery.getResultList();
	}

	private List<Predicate> getCondicoes(Root<Devolucao> root, CriteriaBuilder criteriaBuilder, FilterDevolucao filterDevolucao) {
		List<Predicate> listaPredicate = new ArrayList<>();
		
		if (filterDevolucao.getUsuario() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("usuario"), filterDevolucao.getUsuario());
			
			listaPredicate.add(predicate);
		}
		
		if (filterDevolucao.getProduto() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("estoque").get("produto"), filterDevolucao.getProduto());
			
			listaPredicate.add(predicate);
		}
		
		if (filterDevolucao.getLote() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("estoque").get("lote"), filterDevolucao.getLote());
			
			listaPredicate.add(predicate);
		}
		
		if (filterDevolucao.getDataInicial() != null) {
			Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("dataDevolucao"), filterDevolucao.getDataInicial());
			
			listaPredicate.add(predicate);
		}
		
		if (filterDevolucao.getDataFinal() != null) {
			Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("dataDevolucao"), filterDevolucao.getDataFinal());
			
			listaPredicate.add(predicate);
		}
		
		return listaPredicate;
	}

}
