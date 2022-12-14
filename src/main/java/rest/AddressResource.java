package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.AddressFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("addresses")
public class AddressResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final AddressFacade FACADE =  AddressFacade.getAddressFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddress(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.deleteAddress(id))).build();
    }
}