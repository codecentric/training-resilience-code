package com.codecentric.resilience.hystrix.commands;

import com.codecentric.resilience.hystrix.resource.LatentResource;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConfigurationCommandExample extends HystrixCommand<String> {

    public static final String GROUP_KEY = "configGroupKey";

    private final LatentResource latentResource;

    public ConfigurationCommandExample(int hystrixTimeout, boolean hystrixExecutionTimeoutEnabled,
            LatentResource latentResource) {
        //@formatter:off
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP_KEY))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(hystrixTimeout)
                        .withExecutionTimeoutEnabled(hystrixExecutionTimeoutEnabled))
                        );
        //@formatter:on
        this.latentResource = latentResource;
    }

    @Override
    protected String run() throws Exception {
        return latentResource.getData();
    }

    @Override
    protected String getFallback() {

        return "Fallback: Timeout";
    }
}
