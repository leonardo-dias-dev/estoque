package veterinaria.estoque.repository.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.service.ServiceProduto;

@Dependent
public class FilterProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceProduto serviceProduto;
	
	private String nome;
	
	private LazyDataModel<Produto> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	public FilterProduto() {
		// TODO Auto-generated constructor stub
	}
	
	private class DataTableLazyModel extends LazyDataModel<Produto> {
		
		private static final long serialVersionUID = 1L;

		@Override
		public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceProduto.contarComFiltro(FilterProduto.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Produto> listaProduto = serviceProduto.filtrarPaginado(FilterProduto.this);
			
			return listaProduto;
		}
		
		@Override
		public Object getRowKey(Produto object) {
			return object.getId();
		}
		
		@Override
		public Produto getRowData(String rowKey) {
			for (Produto aux : lazyDataModel) {
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

	public LazyDataModel<Produto> getLazyDataModel() {
		return lazyDataModel;
	}

}
