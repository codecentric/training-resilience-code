package com.codecentric.de.resilience.transport.commands;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import com.codecentric.de.resilience.transport.dto.ConnoteDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConnoteListCommand extends HystrixCommand<ConnoteDTO> {
    private final Client client;

    private final String connoteUrl;

    public ConnoteListCommand(Client client, String connoteUrl) {
        super(HystrixCommandGroupKey.Factory.asKey("ConnoteListCommand"));
        this.client = client;
        this.connoteUrl = connoteUrl;
    }

    @Override
    protected ConnoteDTO run() throws Exception {

        String result =
            client.target(connoteUrl).path("create").path("10").request(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        ConnoteDTO connoteDTO = mapper.readValue(result, ConnoteDTO.class);

        return connoteDTO;
    }

    @Override
    protected ConnoteDTO getFallback() {
        return new ConnoteDTO();
    }
}
