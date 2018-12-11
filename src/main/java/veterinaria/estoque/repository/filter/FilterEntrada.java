package veterinaria.estoque.repository.filter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import veterinaria.estoque.model.entidades.Entrada;
import veterinaria.estoque.model.entidades.Lote;
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
	
	private Lote lote;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private LazyDataModel<Entrada> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	private Calendar calendar;
	
	public FilterEntrada() {
		this.calendar = Calendar.getInstance();
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
		if (dataInicial != null) {
			calendar.setTime(dataInicial);
			
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			this.dataInicial = calendar.getTime();
		} else {
			this.dataInicial = dataInicial;
		}
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		if (dataFinal != null) {
			calendar.setTime(dataFinal);
			
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);

			this.dataFinal = calendar.getTime();
		} else {
			this.dataFinal = dataFinal;
		}
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
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
