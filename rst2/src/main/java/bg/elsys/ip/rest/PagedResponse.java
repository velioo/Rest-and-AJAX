package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.List;

public class PagedResponse {
	
	private List<Anime> data = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private List<String> studios = new ArrayList<>();
	private List<Integer> episodes = new ArrayList<>();
	private List<Integer> years  = new ArrayList<>();
	
	private int page;
	private int totalPages;
	
	public PagedResponse(List<Anime> data, int page, int totalPages, List<String> studios, List<Integer> episodes, List<Integer> years, List<String> types) {
		this.data = data;
		this.page = page;
		this.totalPages = totalPages;
		this.studios = studios;
		this.episodes = episodes;
		this.years = years;
		this.types = types;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<Anime> getData() {
		return data;
	}
	public void setData(List<Anime> data) {
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
	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	public List<Integer> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Integer> episodes) {
		this.episodes = episodes;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}


}
