package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorningTest {

    @Test
    public void goodMorningCommandTest() throws Exception {

        String name = "Jim";

        String result = new CommandGoodMorning(name).execute();

        assertThat(result, is("Hallo, " + name + "!"));
    }
}