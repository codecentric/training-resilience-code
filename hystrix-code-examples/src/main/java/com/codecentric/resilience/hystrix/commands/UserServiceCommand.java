package com.codecentric.resilience.hystrix.commands;

import com.codecentric.resilience.hystrix.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class UserServiceCommand extends HystrixCommand<String> {

    private int id;

    private UserService userService;

    protected UserServiceCommand(final int id, final UserService userService) {
        super(HystrixCommandGroupKey.Factory.asKey("UserServiceGroup"));
        this.id = id;
        this.userService = userService;
    }

    @Override
    protected String run() throws Exception {
        return userService.getUserNameById(id);
    }

    @Override
    protected String getFallback() {
        return "Der User mit der Id " + id + " wurde nicht gefunden";
    }
}
