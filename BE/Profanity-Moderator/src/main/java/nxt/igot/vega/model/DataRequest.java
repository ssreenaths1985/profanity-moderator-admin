package nxt.igot.vega.model;

public class DataRequest {
	Page page;
	boolean moderated;
	Sort sort;
	Filter filter;
	
	public DataRequest(Page page, boolean moderated, Sort sort, Filter filter) {
		super();
		this.page = page;
		this.moderated = moderated;
		this.sort = sort;
		this.filter = filter;
	}
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	public DataRequest() {
		super();
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public boolean isModerated() {
		return moderated;
	}
	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	
}
