package com.codecentric.de.resilience.transport.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.commons.collections.CollectionUtils;

import com.codecentric.de.resilience.transport.commands.ConnoteListCommand;
import com.codecentric.de.resilience.transport.dto.ConnoteDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */

public class ConnoteCache {

    private final static String connoteUrl = "http://localhost:8999/connote";

    // Fallback Cache
    private static List<Long> connoteCache = Collections.synchronizedList(new ArrayList<Long>());

    private static ConnoteCache instance = null;

    protected ConnoteCache() {
    }

    public static ConnoteCache getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (ConnoteCache.class) {
                if (instance == null) {
                    instance = new ConnoteCache();

                    setUp();
                }
            }
        }
        return instance;
    }

    private static void setUp() {
        // Fallback Cache init
        Client client = ClientBuilder.newClient();
        ConnoteListCommand connoteListCommand = new ConnoteListCommand(client, connoteUrl);

        ConnoteDTO connoteDTO = connoteListCommand.execute();

        if (CollectionUtils.isNotEmpty(connoteDTO.getConnoteList()))
            connoteCache.addAll(connoteDTO.getConnoteList());
    }

    /***
     * @return Connote from cached Connotes
     */
    public synchronized Long getConnoteFromCache() {
        // Existiert ein Fallback Cache?
        if (CollectionUtils.isNotEmpty(connoteCache)) {
            Long connoteFallback = connoteCache.get(0);
            connoteCache.remove(0);

            return connoteFallback;
        } else {
            return null;
        }

    }
}
