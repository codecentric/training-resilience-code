package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import rx.Observable;
import rx.Observer;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ReactiveExecutionCommand {

    @Test
    public void reactiveCommand_HotObservable() throws Exception {

        final String james = "James";
        CommandGoodMorning commandGoodMorning = new CommandGoodMorning(james);
        
        String single = null;

        assertThat(single, is("Hallo, " + james + "!"));
        
    }

    @Test
    public void reactiveCommand_ColdObservable() throws Exception {


    }
}

