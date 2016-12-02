package com.codecentric.resilience.hystrix.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class UserServiceCommand extends HystrixCommand<String> {

    private int id;

    protected UserServiceCommand(final int id) {
        super(HystrixCommandGroupKey.Factory.asKey("UserServiceGroup"));
        this.id = id;

    }

    @Override
    protected String run() throws Exception {
        return null;
    }

    @Override
    protected String getFallback() {
        return "Der User mit der Id " + id + " wurde nicht gefunden";
    }
}
