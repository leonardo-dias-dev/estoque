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

import veterinaria.estoque.model.entidades.Saida;
import veterinaria.estoque.repository.filter.FilterSaida;

@Dependent
public class RepositorySaida extends AbstractRepository<Saida, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int contarComFiltro(FilterSaida filterSaida) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Saida> root = criteriaQuery.from(Saida.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterSaida);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(condicoesComoArray);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue();
	}
	
	public List<Saida> filtrarPaginado(FilterSaida filterSaida) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Saida> criteriaQuery = criteriaBuilder.createQuery(Saida.class);
		Root<Saida> root = criteriaQuery.from(Saida.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterSaida);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
		
		criteriaQuery.where(todasCondicoes);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataSaida")));
		
		TypedQuery<Saida> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(filterSaida.getPageSize());
		typedQuery.setFirstResult(filterSaida.getFirst());
		
		return typedQuery.getResultList();
	}

	private List<Predicate> getCondicoes(Root<Saida> root, CriteriaBuilder criteriaBuilder, FilterSaida filterSaida) {
		List<Predicate> listaPredicate = new ArrayList<>();
		
		if (filterSaida.getUsuario() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("usuario"), filterSaida.getUsuario());
			
			listaPredicate.add(predicate);
		}
		
		if (filterSaida.getProduto() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("estoque").get("produto"), filterSaida.getProduto());
			
			listaPredicate.add(predicate);
		}
		
		if (filterSaida.getLote() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("estoque").get("lote"), filterSaida.getLote());
			
			listaPredicate.add(predicate);
		}
		
		if (filterSaida.getDataInicial() != null) {
			Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("dataSaida"), filterSaida.getDataInicial());
			
			listaPredicate.add(predicate);
		}
		
		if (filterSaida.getDataFinal() != null) {
			Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("dataSaida"), filterSaida.getDataFinal());
			
			listaPredicate.add(predicate);
		}
		
		return listaPredicate;
	}
	
}
