package veterinaria.estoque.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class AbstractRepository<T, ID> {
	
	private Class<T> entityClass;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public T buscarPorId(ID id) {
		return entityManager.find(entityClass, id);
	}
	
	public T salvar(T entity) {
		return entityManager.merge(entity);
	}
	
	public void remover(ID id) {
		T entity = entityManager.getReference(entityClass, id);
		
		entityManager.remove(entity);
	}
	
	public void flush() {
		entityManager.flush();
	}
	
	public List<T> buscarTodos() {
		TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
		
		return typedQuery.getResultList();
	}
	
}
