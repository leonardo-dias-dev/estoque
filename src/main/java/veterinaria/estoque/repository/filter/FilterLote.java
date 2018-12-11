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
import veterinaria.estoque.service.ServiceLote;

@Dependent
public class FilterLote implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceLote serviceLote;
	
	private Date dataValidade;
	
	private boolean loteComProdutos;
	
	private LazyDataModel<Lote> lazyDataModel = new DataTableLazyModel();
	
	private int first;
	private int pageSize;
	
	private Calendar calendar;
	
	public FilterLote() {
		this.calendar = Calendar.getInstance();
	}
	
	private class DataTableLazyModel extends LazyDataModel<Lote> {

		private static final long serialVersionUID = 1L;
		
		@Override
		public List<Lote> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			setRowCount(serviceLote.contarComFiltro(FilterLote.this));
			
			setFirst(first);
			setPageSize(pageSize);
			
			List<Lote> listaLote = serviceLote.filtrarPaginado(FilterLote.this);
			
			return listaLote;
		}
		
		@Override
		public Object getRowKey(Lote object) {
			return object.getId();
		}
		
		@Override
		public Lote getRowData(String rowKey) {
			for (Lote aux : lazyDataModel) {
				if(aux.getId().equals(Long.parseLong(rowKey)))
					return aux;
			}
			
			return null;
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

	public LazyDataModel<Lote> getLazyDataModel() {
		return lazyDataModel;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		if (dataValidade != null) {
			calendar.setTime(dataValidade);
			
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			this.dataValidade = calendar.getTime();
		} else {
			this.dataValidade = dataValidade;
		}
	}

	public boolean isLoteComProdutos() {
		return loteComProdutos;
	}

	public void setLoteComProdutos(boolean loteComProdutos) {
		this.loteComProdutos = loteComProdutos;
	}

}
