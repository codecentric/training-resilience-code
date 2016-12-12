package com.codecentric.resilience.hystrix.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorning extends HystrixCommand<String> {

    private String name;

    protected CommandGoodMorning(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("GoodMorningGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hallo, " + name + "!";
    }

    @Override
    protected String getCacheKey() {
        return name;
    }
}
