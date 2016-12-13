package com.codecentric.de.resilience.transport.rest;

import javax.annotation.PreDestroy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import com.codecentric.de.resilience.transport.commands.ConnoteRESTCommand;
import com.codecentric.de.resilience.transport.dto.HystrixMetricDTO;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.turbine.init.TurbineInit;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Path("/transport-booking")
public class TransportBookingService {

    public TransportBookingService() {
        // Hystrix Turbine init
        try {
            TurbineInit.init();
        } catch (Exception e) {
            // doesn´t matter ;-)
        }
    }

    private final static String connoteUrl = "http://localhost:8999/connote";

    @GET
    @Path("/status")
    public Response getStatus() {

        try {
            HystrixCommandGroupKey commandGroup =
                HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getCommandGroup();
            HystrixCommandKey commandKey = HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getCommandKey();

            HystrixCommandMetrics.HealthCounts healthCounts =
                HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getHealthCounts();
            long errorCount =
                HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getHealthCounts().getErrorCount();
            long totalRequests =
                HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getHealthCounts().getTotalRequests();
            int currentConcurrentExecutionCount =
                HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getCurrentConcurrentExecutionCount();
            int averageExecutionTime = HystrixCommandMetrics.getInstance(ConnoteRESTCommand.CONNOTE_KEY).getTotalTimeMean();

            //@formatter:on
            return Response.status(200).entity(new HystrixMetricDTO(commandGroup.name(), commandKey.name(),
                healthCounts.toString(), errorCount, totalRequests, currentConcurrentExecutionCount, averageExecutionTime))
                .build();
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
