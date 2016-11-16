package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AnimeService {
	private static AnimeService instance;
	
	private List<Anime> animeList;
	
	private Anime[] animeArr = {		
	new Anime("Attck on Titan", "TV series", "Production I.G", 25, 2013),
	new Anime("Btooom!", "TV series", "MADHOUSE", 1, 2012),
	new Anime("Code Geass: Lelouch of the Rebellion", "TV series", "Sunrise", 25, 2006),
	new Anime("Death Note", "TV series", "MADHOUSE", 37, 2006),
	new Anime("Eden of the East", "TV series", "Production I.G", 11, 2009),
	new Anime("Ghost in the Shell", "Movie", "Production I.G", 1, 1995),
	new Anime("Gintama", "TV series", "Sunrise", 201, 2006),
	new Anime("Guilty Crown", "TV series", "Production I.G", 22, 2011),
	new Anime("Hyouka", "TV series", "Kyoto Animation", 22, 2012),
	new Anime("K-On!", "TV series", "Kyoto Animation", 13, 2009),
	new Anime("Noragami", "TV series", "Bones", 12, 2014),
	new Anime("Ghost in the Shell: Stand Alone Complex", "TV series", "Production I.G", 26, 2002),
	new Anime("Psycho Pass", "TV series", "Production I.G", 2, 2012),
	new Anime("Kimi ni Todoke", "TV series", "Production I.G", 25, 2009),
	new Anime("Ghost in the Shell 2: Innocence", "Movie", "Production I.G", 1, 2004),
	new Anime("Death Billiards", "Movie", "MADHOUSE", 1, 2013),
	new Anime("One-Punch Man", "TV series", "MADHOUSE", 12, 2015),
	new Anime("Parasyte -the maxim-", "TV series", "MADHOUSE", 24, 2014),
	new Anime("Gintama: Jump Festa 2005", "Special", "Sunrise", 1, 2005),
	new Anime("Gintama: Jump Festa 2008", "Special", "Sunrise", 1, 2008),
	new Anime("The Melancholy of Haruhi Suzumiya", "TV series", "Kyoto Animation", 14, 2006),
	new Anime("Hyouka: Motsubeki Mono wa", "OVA", "Kyoto Animation", 1, 2012),
	new Anime("Beyond the Boundary", "TV series", "Kyoto Animation", 12, 2013),
	new Anime("Darker Than Black - Kuro no Keiyakusha", "TV series", "Bones", 25, 2007),
	new Anime("Darker than Black: Kuro no Keiyakusha Gaiden", "Special", "Bones", 4, 2010),
	new Anime("Noragami OVA", "OVA", "Bones", 2, 2014),
	new Anime("Fullmetal Alchemist: Brotherhood", "TV series", "Bones", 64, 2009),
	new Anime("Fullmetal Alchemist The Movie: Conqueror of Shamballa", "TV series", "Bones", 1, 2005),
	new Anime("The Disappearance of Haruhi Suzumiya", "Movie", "Kyoto Animation", 1, 2010),
	new Anime("K-On!!: Plan!", "Special", "Kyoto Animation", 1, 2011),
	new Anime("Code Geass Gaiden: Boukoku no Akito", "OVA", "Sunrise", 5, 2012),
	new Anime("One-Punch Man: Road to Hero", "OVA", "MADHOUSE", 1, 2015),
	new Anime("Haikyuu!!", "TV series", "Production I.G", 25, 2014)};
	
	public static AnimeService getInstance() {
		if (instance == null) {
			instance = new AnimeService();
		}
		return instance;
	}
	
	public AnimeService() {
		animeList = new ArrayList<Anime>(Arrays.asList(animeArr));
	}
	
	public void insertAnime(Anime anime) {
		animeList.add(anime);
	}
	
	public List getAnimes() {
		return Collections.unmodifiableList(animeList);
	}
	
	public PagedResponse getAnimesInPagesFiltered(int page, int perPage, String withName) {
		long previousEntries = page * perPage;
		List<Anime> pageOfAnimes = animeList.stream()
							.filter((u) -> u.getName().equals(withName) || withName == null)
							.skip(previousEntries)
							.limit(perPage)
							.collect(Collectors.toList());
		int totalPages = (int) Math.ceil(((double)animeList.size() / perPage));
		PagedResponse response = new PagedResponse(pageOfAnimes, page, totalPages);
		
		return response;
	}
	
	public List<String> getAllDistinctNames() {
		return animeList.stream()
				.map((u) -> u.getName())
				.distinct()
				.collect(Collectors.toList());
	}
}






