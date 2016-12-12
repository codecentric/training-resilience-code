package com.codecentric.resilience.hystrix.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CacheTestCommand {

    @Test
    public void chacheTestGoodMorning() throws Exception {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {

            CommandGoodMorning commandGoodMorning = new CommandGoodMorning("value_1");
            commandGoodMorning.execute();

            assertFalse(commandGoodMorning.isResponseFromCache());

            CommandGoodMorning commandGoodMorning2 = new CommandGoodMorning("value_1");
            commandGoodMorning2.execute();

            assertTrue(commandGoodMorning2.isResponseFromCache());


            CommandGoodMorning commandGoodMorning3 = new CommandGoodMorning("value_2");
            commandGoodMorning3.execute();

            assertFalse(commandGoodMorning3.isResponseFromCache());


        } finally {
            context.shutdown();
        }

    }
}
