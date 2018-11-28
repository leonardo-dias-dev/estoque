package veterinaria.estoque.repository.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import veterinaria.estoque.model.entidades.Estoque;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.service.ServiceEstoque;

@Dependent
public class FilterEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceEstoque serviceEstoque;
	
	private Produto produto;
	
	private LazyDataModel<Estoque> lazyDataModel = new DataTableLazyModel();

	private int first;
	private int pageSize;
	
	public FilterEstoque() {
		// TODO Auto-generated constructor stub
	}
	
	private class DataTableLazyModel extends LazyDataModel<Estoque> {

		private static final long serialVersionUID = 1L;
		
		@Override
		public List<Estoque> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceEstoque.contarComFiltro(FilterEstoque.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Estoque> listaProduto = serviceEstoque.filtrarPaginado(FilterEstoque.this);
			
			return listaProduto;
		}
		
		@Override
		public Object getRowKey(Estoque object) {
			return object.getId();
		}
		
		@Override
		public Estoque getRowData(String rowKey) {
			for (Estoque aux : lazyDataModel) {
				if(aux.getId().equals(Long.parseLong(rowKey)))
					return aux;
			}
			
			return null;
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public LazyDataModel<Estoque> getLazyDataModel() {
		return lazyDataModel;
	}

}
