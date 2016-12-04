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

        // Hot Observable
        Observable<String> observe = commandGoodMorning.observe();

        String single = observe.toBlocking().single();

        assertThat(single, is("Hallo, " + james + "!"));

    }

    @Test
    public void reactiveCommand_ColdObservable() throws Exception {

        final String james = "James";
        final String result = "Hallo, " + james + "!";

        CommandGoodMorning commandGoodMorning = new CommandGoodMorning(james);

        // Cold Observable
        Observable<String> observeCold = commandGoodMorning.toObservable();

        observeCold.subscribe(new Observer<String>() {
            public void onCompleted() {
                System.out.println("--- Completed ---");
            }

            public void onError(Throwable throwable) {
                System.out.println("--- Error ---");
            }

            public void onNext(String s) {
                assertThat(s, is(result));
            }
        });

    }
}
