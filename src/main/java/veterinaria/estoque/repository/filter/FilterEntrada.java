package veterinaria.estoque.repository.filter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.service.ServiceEntrada;

@Dependent
public class FilterEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceEntrada serviceEntrada;
	
	private Usuario usuario;
	
	private Produto produto;
	
	private String lote;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private LazyDataModel<Entrada> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	public FilterEntrada() {
		// TODO Auto-generated constructor stub
	}
	
	private class DataTableLazyModel extends LazyDataModel<Entrada> {
		
		private static final long serialVersionUID = 1L;

		@Override
		public List<Entrada> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceEntrada.contarComFiltro(FilterEntrada.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Entrada> listaProduto = serviceEntrada.filtrarPaginado(FilterEntrada.this);
			
			return listaProduto;
		}
		
		@Override
		public Object getRowKey(Entrada object) {
			return object.getId();
		}
		
		@Override
		public Entrada getRowData(String rowKey) {
			for (Entrada aux : lazyDataModel) {
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

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
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

	public LazyDataModel<Entrada> getLazyDataModel() {
		return lazyDataModel;
	}

}
