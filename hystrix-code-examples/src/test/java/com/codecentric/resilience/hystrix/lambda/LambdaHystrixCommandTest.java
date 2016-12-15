package com.codecentric.resilience.hystrix.lambda;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import java.util.function.Supplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdaHystrixCommandTest {

    @Mock
    private Supplier<String> stringRunSupplierMock;

    @Test
    public void lambdaTest() throws Exception {

        String run = "Run it";
        String fallback = "Fallback";

        Supplier<String> stringRunSupplier = () -> run;
        Supplier<String> stringFallbackSupplier = () -> fallback;

        LambdaHystrixCommand<String> lambdaHystrixCommand =
            new LambdaHystrixCommand<>("lambdaCommand", "lambdaGroupKey", stringRunSupplier, stringFallbackSupplier);

        String execute = lambdaHystrixCommand.execute();

        assertThat(execute, is(run));

    }

    @Test
    public void lambdaTest_Fallback() throws Exception {

        String run = "Run it";
        String fallback = "Fallback";

        Supplier<String> stringFallbackSupplier = () -> fallback;

        when(stringRunSupplierMock.get()).thenThrow(new RuntimeException("Error"));

        LambdaHystrixCommand<String> lambdaHystrixCommand =
            new LambdaHystrixCommand<>("lambdaCommand", "lambdaGroupKey", stringRunSupplierMock, stringFallbackSupplier);

        String execute = lambdaHystrixCommand.execute();

        assertThat(execute, is(fallback));

    }
}