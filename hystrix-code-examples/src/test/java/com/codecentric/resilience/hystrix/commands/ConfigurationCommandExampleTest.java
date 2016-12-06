package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import com.codecentric.resilience.hystrix.resource.LatentResource;
import com.netflix.hystrix.Hystrix;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConfigurationCommandExampleTest {

    @Before
    public void setUp() throws Exception {
        Hystrix.reset();

    }

    @Test
    public void timeout_Goodcase() throws Exception {

        LatentResource latentResource = new LatentResource(30);
        int hystrixTimeout = 50;
        boolean hystrixTimeoutEnabled = true;

        ConfigurationCommandExample configurationCommandExample =
            new ConfigurationCommandExample(hystrixTimeout, hystrixTimeoutEnabled, latentResource);

        String result = configurationCommandExample.execute();

        assertThat(result, is("Here it is..."));
    }

    @Test
    public void timeout_Fallback() throws Exception {

        LatentResource latentResource = new LatentResource(50);
        int hystrixTimeout = 50;
        boolean hystrixTimeoutEnabled = true;

        ConfigurationCommandExample configurationCommandExample =
                new ConfigurationCommandExample(hystrixTimeout, hystrixTimeoutEnabled, latentResource);

        String result = configurationCommandExample.execute();

        assertThat(result, is("Fallback: Timeout"));
    }

    @Test
    public void timeout_Disabled() throws Exception {

        LatentResource latentResource = new LatentResource(50);
        int hystrixTimeout = 30;
        boolean hystrixTimeoutEnabled = false;

        ConfigurationCommandExample configurationCommandExample =
            new ConfigurationCommandExample(hystrixTimeout, hystrixTimeoutEnabled, latentResource);

        String result = configurationCommandExample.execute();

        assertThat(result, is("Here it is..."));
    }


}