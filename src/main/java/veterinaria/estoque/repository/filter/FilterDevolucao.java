package veterinaria.estoque.repository.filter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import veterinaria.estoque.model.entidades.Devolucao;
import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.service.ServiceDevolucao;

@Dependent
public class FilterDevolucao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceDevolucao serviceDevolucao;
	
	private Usuario usuario;
	
	private Produto produto;
	
	private Lote lote;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private LazyDataModel<Devolucao> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	public FilterDevolucao() {
		// TODO Auto-generated constructor stub
	}
	
	private class DataTableLazyModel extends LazyDataModel<Devolucao> {

		private static final long serialVersionUID = 1L;
		
		@Override
		public List<Devolucao> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceDevolucao.contarComFiltro(FilterDevolucao.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Devolucao> listaProduto = serviceDevolucao.filtrarPaginado(FilterDevolucao.this);
			
			return listaProduto;
		}
		
		@Override
		public Object getRowKey(Devolucao object) {
			return object.getId();
		}
		
		@Override
		public Devolucao getRowData(String rowKey) {
			for (Devolucao aux : lazyDataModel) {
				if(aux.getId().equals(Long.parseLong(rowKey)))
					return aux;
			}
			
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
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

	public LazyDataModel<Devolucao> getLazyDataModel() {
		return lazyDataModel;
	}

}
