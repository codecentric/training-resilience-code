package com.codecentric.de.resilience.transport.rest;

import javax.annotation.PreDestroy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import com.codecentric.de.resilience.transport.cache.ConnoteCache;
import com.codecentric.de.resilience.transport.commands.ConnoteRESTCommand;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCommandMetrics;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Path("/transport-booking")
public class TransportBookingService {

    private final static String connoteUrl = "http://localhost:8999/connote";

    private ConnoteCache connoteCache;

    @GET
    @Path("/status")
    public Response getStatus() {

        String newLine = "\b";

        try {
            //@formatter:off
        String hystrixStatus = new StringBuilder("Hystrix Status")
                .append(newLine)
                .append("GroupKey:")
                .append(HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getCommandGroup())
                .append(newLine)
                .append("CommandKey:")
                .append(HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getCommandKey())
                .append(newLine)
                .append(HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getHealthCounts())
                .append(newLine)
                .append("Error Count: ")
                .append(HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getHealthCounts().getErrorCount())
                .append(newLine)
                .append("Total Requests: ")
                .append(HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getHealthCounts().getTotalRequests())
                .append(newLine)
                .append("CurrentConcurrentExecutionCount:")
                .append(newLine)
                .append(HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getCurrentConcurrentExecutionCount())
                .toString();
        //@formatter:on
            return Response.status(200).entity(hystrixStatus).build();
        } catch (Exception e) {
            // keine Statusinfos vorhanden...
            return Response.status(200).entity("ok - unable to get hystrix metrics").build();
        }

    }

    @GET
    @Path("/create")
    public Response createBooking() {
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
