package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorningFallbackTest {

    @Test
    public void goodMorningCommandFallbackTest() throws Exception {

        String name = "Jim";

        String result = new CommandGoodMorningFallback(name).execute();

        assertThat(result, is("Hallo, " + name + "!"));

    }

}