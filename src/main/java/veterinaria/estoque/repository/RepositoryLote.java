package veterinaria.estoque.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;

import veterinaria.estoque.model.entidades.Lote;

@Dependent
public class RepositoryLote extends AbstractRepository<Lote, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<Lote> buscarPorNumero(String numero) {
		TypedQuery<Lote> typedQuery = getEntityManager().createQuery("FROM Lote WHERE numero LIKE :numero ORDER BY numero", Lote.class);
		
		typedQuery.setParameter("numero", "%" + numero + "%");
		
		return typedQuery.getResultList();
	}

}
