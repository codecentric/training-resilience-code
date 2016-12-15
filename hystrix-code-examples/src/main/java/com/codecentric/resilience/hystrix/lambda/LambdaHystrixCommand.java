package com.codecentric.resilience.hystrix.lambda;

import java.util.function.Supplier;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class LambdaHystrixCommand<T> extends HystrixCommand<T> {

    private final Supplier<T> runSup;

    private final Supplier<T> fallbackSup;



    protected LambdaHystrixCommand(String cmdkey, String cmdGroupKey, Supplier<T> runSup, Supplier<T> fallbackSup) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(cmdGroupKey))
            .andCommandKey(HystrixCommandKey.Factory.asKey(cmdkey)));
        this.runSup = runSup;
        this.fallbackSup = fallbackSup;
    }

    @Override
    protected T run() throws Exception {
        return runSup.get();
    }

    @Override
    protected T getFallback() {
        return fallbackSup.get();
    }
}
