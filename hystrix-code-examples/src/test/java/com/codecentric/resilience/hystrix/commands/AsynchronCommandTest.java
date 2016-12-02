package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class AsynchronCommandTest {

    @Test
    public void goodMorning_Asynchron() throws Exception {

        String name = "Jim";
        CommandGoodMorning goodMorning = new CommandGoodMorning(name);

        String resultExpected = null;

        assertThat(resultExpected, is("Hallo, " + name));

    }
}
