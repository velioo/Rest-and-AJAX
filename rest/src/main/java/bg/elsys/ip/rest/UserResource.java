package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/users")
@Api("users")
public class UserResource {
	@GET
	@ApiOperation("get list of users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "velioo"));
		return Response.ok(users).build();
	}
}
