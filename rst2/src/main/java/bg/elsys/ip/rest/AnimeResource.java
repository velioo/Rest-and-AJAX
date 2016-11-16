package bg.elsys.ip.rest;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/animes")
@Api(value = "get all animes")
public class AnimeResource {
	@GET
	@ApiOperation(value = "get list of animes")
	@Produces(MediaType.APPLICATION_JSON)
	public PagedResponse getAnimes(@QueryParam("page") Integer page, @QueryParam("perPage") Integer perPage, 
			@QueryParam("withname") String withName) {
		
		AnimeService animeService = AnimeService.getInstance();	
		PagedResponse animesInPages = animeService.getAnimesInPagesFiltered(page, perPage, withName);
		
		return animesInPages;
	}
	
	@POST
	@ApiOperation(value = "add anime")
	@Path("/add_anime")
	public Response createUser(@FormParam("name") String name, @FormParam("type") String type,
			@FormParam("studio") String studio, @FormParam("episodes") String episodes,  @FormParam("air_year") String air_year) throws URISyntaxException {
		
		Anime anime = new Anime(name, type, studio, Integer.parseInt(episodes), Integer.parseInt(air_year));
		AnimeService animeService = AnimeService.getInstance();	
		animeService.insertAnime(anime);
		
		java.net.URI location = new java.net.URI("http://localhost:8080/rst2/show_page.html");
	    return Response.temporaryRedirect(location).build();
	}
	

}




