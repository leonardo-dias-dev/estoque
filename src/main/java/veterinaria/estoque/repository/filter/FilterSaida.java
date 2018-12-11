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

import veterinaria.estoque.model.entidades.Lote;
import veterinaria.estoque.model.entidades.Produto;
import veterinaria.estoque.model.entidades.Saida;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.service.ServiceSaida;

@Dependent
public class FilterSaida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceSaida serviceSaida;
	
	private Usuario usuario;
	
	private Produto produto;
	
	private Lote lote;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private LazyDataModel<Saida> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	private Calendar calendar;
	
	public FilterSaida() {
		this.calendar = Calendar.getInstance();
	}
	
	private class DataTableLazyModel extends LazyDataModel<Saida> {

		private static final long serialVersionUID = 1L;
		
		@Override
		public List<Saida> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceSaida.contarComFiltro(FilterSaida.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Saida> listaProduto = serviceSaida.filtrarPaginado(FilterSaida.this);
			
			return listaProduto;
		}
		
		@Override
		public Object getRowKey(Saida object) {
			return object.getId();
		}
		
		@Override
		public Saida getRowData(String rowKey) {
			for (Saida aux : lazyDataModel) {
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

	public LazyDataModel<Saida> getLazyDataModel() {
		return lazyDataModel;
	}

}
