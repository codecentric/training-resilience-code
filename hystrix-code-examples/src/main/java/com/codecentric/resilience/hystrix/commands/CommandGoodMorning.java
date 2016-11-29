package com.codecentric.resilience.hystrix.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorning extends HystrixCommand<String> {

    protected CommandGoodMorning(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(null));
    }

    protected String run() throws Exception {
        return null;
    }
}
