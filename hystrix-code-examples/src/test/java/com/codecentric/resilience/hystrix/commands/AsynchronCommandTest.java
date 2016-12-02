package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.concurrent.Future;
import org.junit.Test;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class AsynchronCommandTest {

    @Test
    public void goodMorning_Asynchron() throws Exception {

        String name = "Jim";
        CommandGoodMorning goodMorning = new CommandGoodMorning(name);

        Future<String> stringFuture = goodMorning.queue();

        String resultExpected = stringFuture.get();

        assertThat(resultExpected, is("Hallo, " + name + "!"));

    }
}
