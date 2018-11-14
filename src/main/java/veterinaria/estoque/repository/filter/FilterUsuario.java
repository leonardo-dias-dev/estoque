package veterinaria.estoque.repository.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.service.ServiceUsuario;

@Dependent
public class FilterUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUsuario serviceUsuario;
	
	private String nome;
	
	private LazyDataModel<Usuario> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	public FilterUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	private class DataTableLazyModel extends LazyDataModel<Usuario> {

		private static final long serialVersionUID = 1L;
		
		@Override
		public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceUsuario.contarComFiltro(FilterUsuario.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Usuario> listaUsuario = serviceUsuario.filtrarPaginado(FilterUsuario.this);
			
			return listaUsuario;
		}
		
		@Override
		public Object getRowKey(Usuario object) {
			return object.getId();
		}

		@Override
		public Usuario getRowData(String rowKey) {
			for (Usuario aux : lazyDataModel) {
				if(aux.getId().equals(Long.parseLong(rowKey)))
					return aux;
			}
			
			return null;
		}
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public LazyDataModel<Usuario> getLazyDataModel() {
		return lazyDataModel;
	}

}
