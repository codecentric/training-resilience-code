package com.codecentric.de.resilience.transport.rest;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import com.codecentric.de.resilience.transport.cache.ConnoteCache;
import com.codecentric.de.resilience.transport.commands.ConnoteRESTCommand;
import com.netflix.hystrix.Hystrix;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Path("/transport-booking")
public class TransportBookingService {

    private final static String connoteUrl = "http://localhost:8999/connote";

    @EJB
    private ConnoteCache connoteCache;

    @GET
    @Path("/status")
    public Response getStatus() {

        return Response.status(200).entity("ok").build();

    }

    @GET
    @Path("/create")
    public Response createBooking2() {
        Client client = ClientBuilder.newClient();

        String response = new ConnoteRESTCommand(client, connoteUrl).execute();

        return Response.status(200).entity(response).build();
    }

    @PreDestroy
    private void tearDown() {
        // Thread Pools down
        Hystrix.reset();
    }
}
