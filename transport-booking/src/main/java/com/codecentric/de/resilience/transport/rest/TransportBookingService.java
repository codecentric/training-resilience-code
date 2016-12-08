package com.codecentric.de.resilience.transport.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Path("/transport-booking")
public class TransportBookingService {

    private final static String connoteUrl = "http://localhost:8999/connote";

    @GET
    @Path("/status")
    public Response getStatus() {

        return Response.status(200).entity("ok").build();

    }

    @GET
    @Path("/create")
    public Response createBooking() {
        Client client = ClientBuilder.newClient();

        //@formatter:off
        String response = client.target(connoteUrl)
                .path("create-chaos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        //@formatter:on

        return Response.status(200).entity(response).build();
    }
}
