package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.List;

public class PagedResponse {
	
	private List<User> data = new ArrayList<>();
	
	private int page;
	private int totalPages;
	
	
	public PagedResponse(List<User> data, int page, int totalPages) {
		this.data = data;
		this.page = page;
		this.totalPages = totalPages;
	}
	
	public List<User> getData() {
		return data;
	}
	public void setData(List<User> data) {
		this.data = data;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	

}
