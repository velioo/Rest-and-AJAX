package bg.elsys.ip.rest;

import io.swagger.annotations.ApiModelProperty;

public class Anime {
	
	@ApiModelProperty(required = true)
	String name;
	String type;
	String studio;
	Integer airYear;
	Integer episodes;

	public Anime() {
	}

	public Anime(String name, String type, String studio, Integer episodes, Integer airYear) {
		super();
		this.name = name;
		this.type = type;
		this.studio = studio;
		this.episodes = episodes;
		this.airYear = airYear;
	}
	
	public Integer getAirYear() {
		return airYear;
	}

	public void setAirYear(int airYear) {
		this.airYear = airYear;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public Integer getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
