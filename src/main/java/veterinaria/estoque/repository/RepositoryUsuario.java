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

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.repository.filter.FilterUsuario;

@Dependent
public class RepositoryUsuario extends AbstractRepository<Usuario, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Usuario buscarPorLogin(String login) {
		TypedQuery<Usuario> typedQuery = getEntityManager().createQuery("FROM Usuario WHERE login = :login", Usuario.class);
		
		typedQuery.setParameter("login", login);
		
		return typedQuery.getSingleResult();
	}
	
	public boolean existePorCpf(String cpf)  {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		Predicate predicate = criteriaBuilder.like(root.get("cpf"), "%" + cpf + "%");
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(predicate);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue() > 0;
	}
	
	public boolean existePorEmail(String email)  {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		Predicate predicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(predicate);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue() > 0;
	}
	
	public int contarComFiltro(FilterUsuario filterUsuario) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterUsuario);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		
		criteriaQuery.select(criteriaBuilder.count(root)).where(condicoesComoArray);
		
		TypedQuery<Long> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		Long qtd = typedQuery.getSingleResult();
		
		return qtd.intValue();
	}
	
	public List<Usuario> filtrarPaginado(FilterUsuario filterUsuario) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		List<Predicate> condicoes = getCondicoes(root, criteriaBuilder, filterUsuario);
		Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);
		Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
		
		criteriaQuery.where(todasCondicoes);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));
		
		TypedQuery<Usuario> typedQuery = getEntityManager().createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(filterUsuario.getPageSize());
		typedQuery.setFirstResult(filterUsuario.getFirst());
		
		return typedQuery.getResultList();
	}

	private List<Predicate> getCondicoes(Root<Usuario> root, CriteriaBuilder criteriaBuilder, FilterUsuario filterUsuario) {
		List<Predicate> listaPredicate = new ArrayList<>();
		
		if (filterUsuario.getNome() != null) {
			Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filterUsuario.getNome() + "%");
			
			listaPredicate.add(predicate);
		}
		
		return listaPredicate;
	}

}
