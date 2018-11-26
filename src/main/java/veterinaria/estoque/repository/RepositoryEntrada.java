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

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.repository.filter.FilterEntrada;

@Dependent
public class RepositoryEntrada extends AbstractRepository<Entrada, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int contarComFiltro(FilterEntrada filterEntrada) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Entrada> root = criteriaQuery.from(Entrada.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterEntrada);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(condicoesComoArray);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue();
	}
	
	public List<Entrada> filtrarPaginado(FilterEntrada filterEntrada) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Entrada> criteriaQuery = criteriaBuilder.createQuery(Entrada.class);
		Root<Entrada> root = criteriaQuery.from(Entrada.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterEntrada);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
		
		criteriaQuery.where(todasCondicoes);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataEntrada")));
		
		TypedQuery<Entrada> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(filterEntrada.getPageSize());
		typedQuery.setFirstResult(filterEntrada.getFirst());
		
		return typedQuery.getResultList();
	}

	private List<Predicate> getCondicoes(Root<Entrada> root, CriteriaBuilder criteriaBuilder, FilterEntrada filterEntrada) {
		List<Predicate> listaPredicate = new ArrayList<>();
		
		if (filterEntrada.getUsuario() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("usuario"), filterEntrada.getUsuario());
			
			listaPredicate.add(predicate);
		}
		
		if (filterEntrada.getProduto() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("produto"), filterEntrada.getProduto());
			
			listaPredicate.add(predicate);
		}
		
		if (filterEntrada.getLote() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("lote"), filterEntrada.getLote());
			
			listaPredicate.add(predicate);
		}
		
		if (filterEntrada.getDataInicial() != null) {
			Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("dataEntrada"), filterEntrada.getDataInicial());
			
			listaPredicate.add(predicate);
		}
		
		if (filterEntrada.getDataFinal() != null) {
			Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("dataEntrada"), filterEntrada.getDataFinal());
			
			listaPredicate.add(predicate);
		}
		
		return listaPredicate;
	}

}
