package com.codecentric.resilience.hystrix.commands;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorningTest {

    @Test
    public void goodMorningCommandTest() throws Exception {

        String result = new CommandGoodMorning(null).execute();

        assertThat(result, is("Hallo, xxDeinNamexx!"));
    }
}