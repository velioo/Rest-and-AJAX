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
import io.swagger.annotations.ApiParam;

@Path("/animes")
@Api(value = "Get and add items")
public class AnimeResource {
	@GET
	@ApiOperation(value = "get list of items")
	@Produces(MediaType.APPLICATION_JSON)
	public PagedResponse getAnimes(@ApiParam(value = "Page to start from", required = true) @QueryParam("page") Integer page, 
			@ApiParam(value = "Items per page", required = true) @QueryParam("perPage") Integer perPage, 
			@ApiParam(value = "How should the items's name start") @QueryParam("withName") String withName,
			@ApiParam(value = "The filters to apply to the items. <br/>"
					+ "Format = 'a,b,c,d' <br/><br/>"
					+ "'a' - The item's type <br/>"
					+ "'b' - The item's studio </br>"
					+ "'c' - The item's num of episodes <br/>"
					+ "'d' - The item's air year<br/><br/>"
					+ "Default = 'all,all,all,all'") @QueryParam("filters") String filters) {
		
		AnimeService animeService = AnimeService.getInstance();	
		PagedResponse animesInPages = animeService.getAnimesInPagesFiltered(page, perPage, withName, filters);
		
		return animesInPages;
	}
	
	@POST
	@ApiOperation(value = "add a new item")
	@Path("/add_anime")
	public Response createUser(@ApiParam(value = "The item's name", required = true) @FormParam("name") String name,
			@ApiParam(value = "The item's type", required = true) @FormParam("type") String type,
			@ApiParam(value = "The item's studio", required = true) @FormParam("studio") String studio, 
			@ApiParam(value = "The item's num of episodes <br/> Must be a number", required = true) @FormParam("episodes") String episodes,  
			@ApiParam(value = "The item's air year <br/> Must be a number", required = true) @FormParam("air_year") String air_year) throws URISyntaxException {
		
		Anime anime = new Anime(name, type, studio, Integer.parseInt(episodes), Integer.parseInt(air_year));
		AnimeService animeService = AnimeService.getInstance();	
		animeService.insertAnime(anime);
		
		java.net.URI location = new java.net.URI("http://localhost:8080/rst2/show_page.html");
	    return Response.temporaryRedirect(location).build();
	}
	

}




