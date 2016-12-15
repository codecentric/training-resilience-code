package com.codecentric.de.resilience.transport.commands;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import com.codecentric.de.resilience.transport.cache.ConnoteCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConnoteRESTCommand extends HystrixCommand<String> {

    public static final String COMMAND_GROUPKEY = "ConnoteRESTCommandGroupKey";

    public static final HystrixCommandKey CONNOTE_KEY = HystrixCommandKey.Factory.asKey("ConnoteCommand");

    private final Client client;

    private final String connoteUrl;

    public ConnoteRESTCommand(Client client, String connoteUrl) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(COMMAND_GROUPKEY)).andCommandKey(CONNOTE_KEY));
        this.client = client;
        this.connoteUrl = connoteUrl;
    }

    @Override
    protected String run() throws Exception {
        return client.target(connoteUrl).path("create-chaos").request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
    }

    @Override
    protected String getFallback() {

        // Existiert ein Fallback Cache?
        Long connoteFromCache = ConnoteCache.getInstance().getConnoteFromCache();
        if (connoteFromCache == null) {


            return "{connote-error:" + getExecutionException().getMessage() + "}";
        } else
            return "{connote-fallback: " + connoteFromCache + "}";
    }
}
