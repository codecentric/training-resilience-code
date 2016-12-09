package com.codecentric.de.resilience.transport.commands;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import com.codecentric.de.resilience.transport.cache.ConnoteCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConnoteRESTCommand extends HystrixCommand<String> {

    private static final String COMMAND_GROUPKEY = "ConnoteRESTCommandGroupKey";

    private final Client client;

    private final String connoteUrl;


    public ConnoteRESTCommand(Client client, String connoteUrl) {
        super(HystrixCommandGroupKey.Factory.asKey(COMMAND_GROUPKEY));
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
