package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorningExceptionWrappingTest {

    @Test
    public void goodMorningCommandFallbackTest() throws Exception {

        String name = "Jim";

        String result = new CommandGoodMorningExceptionWrapping(name).execute();

        assertThat(result, is("I mag nid! ;-) "));

    }

}