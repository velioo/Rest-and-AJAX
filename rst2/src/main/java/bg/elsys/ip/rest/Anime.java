package bg.elsys.ip.rest;

public class Anime {
	String name;
	String type;
	String studio;
	int air_year;
	int episodes;

	public Anime() {
		
	}

	public Anime(String name, String type, String studio, int episodes, int air_year) {
		super();
		this.name = name;
		this.type = type;
		this.studio = studio;
		this.episodes = episodes;
		this.air_year = air_year;
	}
	
	public int getAir_year() {
		return air_year;
	}

	public void setAir_year(int air_year) {
		this.air_year = air_year;
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

	public int getEpisodes() {
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
