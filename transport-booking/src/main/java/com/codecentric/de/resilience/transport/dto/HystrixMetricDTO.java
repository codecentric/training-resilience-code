package com.codecentric.de.resilience.transport.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Benjamin Wilms (xd98870)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HystrixMetricDTO {

    private String commandGroupKey;

    private String commandKey;

    private String healthCounts;

    private long errorCount;

    private long totalRequests;

    private int concurrentExecutionCount;

    private int averageExecutionTime;

    public HystrixMetricDTO() {
    }

    public HystrixMetricDTO(String commandGroupKey, String commandKey, String healthCounts, long errorCount, long totalRequests,
            int concurrentExecutionCount, int averageExecutionTime) {
        this.commandGroupKey = commandGroupKey;
        this.commandKey = commandKey;
        this.healthCounts = healthCounts;
        this.errorCount = errorCount;
        this.totalRequests = totalRequests;
        this.concurrentExecutionCount = concurrentExecutionCount;
        this.averageExecutionTime = averageExecutionTime;
    }

    public String getCommandGroupKey() {
        return commandGroupKey;
    }

    public void setCommandGroupKey(String commandGroupKey) {
        this.commandGroupKey = commandGroupKey;
    }

    public String getCommandKey() {
        return commandKey;
    }

    public void setCommandKey(String commandKey) {
        this.commandKey = commandKey;
    }

    public String getHealthCounts() {
        return healthCounts;
    }

    public void setHealthCounts(String healthCounts) {
        this.healthCounts = healthCounts;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }

    public int getConcurrentExecutionCount() {
        return concurrentExecutionCount;
    }

    public void setConcurrentExecutionCount(int concurrentExecutionCount) {
        this.concurrentExecutionCount = concurrentExecutionCount;
    }

    public int getAverageExecutionTime() {
        return averageExecutionTime;
    }

    public void setAverageExecutionTime(int averageExecutionTime) {
        this.averageExecutionTime = averageExecutionTime;
    }
}
