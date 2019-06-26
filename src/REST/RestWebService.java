package REST;

import DAO.IPersonDAO;
import Person.Person;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestWebService {

    @EJB
    private IPersonDAO ejb;

    @Context
    private UriInfo context;

    @GET
    public Response returnAll() {
        return Response.ok(ejb.returnAllPeople()).build();
    }

    @POST
    @Path("/user")
    public Response addUser(Person p) {
        ejb.save(p);
        return Response.ok(p).build();
    }

    @PUT
    @Path("/user/{email}")
    public Response editUser(@PathParam("email") String email,Person p) {

            ejb.save(p);
            return Response.ok().build();

    }

    @DELETE
    @Path("/user/{email}")
    public Response izbrisiOsebo(@PathParam("email") String email) {
        Person p=ejb.findOsebaByEmail(email);
        if (p != null) {
            ejb.deletePerson(email);
            return Response.ok().build();
        } else {
            return Response.status(403).entity("OsebeNiMogoceNajtiException").build();
        }
    }

}
