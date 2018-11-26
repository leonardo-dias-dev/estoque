package veterinaria.estoque.repository;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import veterinaria.estoque.model.entidades.Estoque;

@Dependent
public class RepositoryEstoque extends AbstractRepository<Estoque, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

}
