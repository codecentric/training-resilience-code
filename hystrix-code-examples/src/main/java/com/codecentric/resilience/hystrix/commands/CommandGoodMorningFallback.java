package com.codecentric.resilience.hystrix.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class CommandGoodMorningFallback extends HystrixCommand<String> {
    private String name;

    protected CommandGoodMorningFallback(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("GoodMorningGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        throw new RuntimeException("I mag nid! ;-) ");
    }

    @Override
    protected String getFallback() {
        return "Hallo, " + name + "!";
    }
}
