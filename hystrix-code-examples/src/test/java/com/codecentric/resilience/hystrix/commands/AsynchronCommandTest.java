package com.codecentric.resilience.hystrix.commands;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class AsynchronCommandTest {

    private HystrixRequestContext context;

    @Before public void setUp() throws Exception {
        context = HystrixRequestContext.initializeContext();

    }

    @Test public void goodMorning_Asynchron() throws Exception {

        String name = "Jim";
        CommandGoodMorning goodMorning = new CommandGoodMorning(name);

        Future<String> stringFuture = goodMorning.queue();

        String resultExpected = stringFuture.get();

        assertThat(resultExpected, is("Hallo, " + name + "!"));

    }

    @After public void tearDown() throws Exception {
        context.shutdown();

    }
}
