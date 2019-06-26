package REST;

import Activity.Activity;
import DAO.IActivityDAO;
import DAO.IPersonDAO;
import Person.Person;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestWebServiceActivity {


        @EJB
        private IActivityDAO ejb;

        @EJB
        private IPersonDAO ejbPerson;

        @Context
        private UriInfo context;

        @GET
        @Path("/{email}")
        public Response returnAll(@PathParam("email") String email) {
            Person p = ejbPerson.findOsebaByEmail(email);
            return Response.ok(ejb.getPersonActivites(p)).build();
        }

        @POST
        @Path("/activity")
        public Response addActivity(Activity a) throws Exception {
            ejb.save(a);
            return Response.ok(a).build();
        }

        @PUT
        @Path("/activity/{id}")
        public Response editActivity(@PathParam("id") int id, Activity a) throws Exception {

            ejb.save(a);
            return Response.ok().build();

        }

        @DELETE
        @Path("/activity/{id}")
        public Response deleteActivity(@PathParam("id") int id) {
            Activity a = ejb.getActivityByID(id);
            if (a != null) {
                ejb.deleteActivity(id);
                return Response.ok().build();
            } else {
                return Response.status(403).entity("AktivnostiNiMogoceNajtiException").build();
            }
        }
    }

