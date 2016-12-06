package com.codecentric.resilience.hystrix.resource;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class LatentResource {

    private int latency;

    public LatentResource(int latency) {
        this.latency = (latency < 0) ? 0 : latency;
    }

    public String getData() {
        addLatency();
        return "Here it is...";
    }

    private void addLatency() {
        try {
            Thread.sleep(latency);
        } catch (InterruptedException e) {
            // we do not care about
        }
    }
}
