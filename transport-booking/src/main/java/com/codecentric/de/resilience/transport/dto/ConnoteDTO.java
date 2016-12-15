package com.codecentric.de.resilience.transport.dto;

import java.util.List;
import org.joda.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Benjamin Wilms (xd98870)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnoteDTO {

    private boolean fallback;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isFallback() {
        return fallback;
    }

    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }

    private Long connote;

    private List<Long> connoteList;

    private LocalDateTime created;

    public ConnoteDTO() {
        // JSON Object Mapper
    }

    public ConnoteDTO(boolean fallback, String errorMessage, Long connote) {
        this.fallback = fallback;
        this.errorMessage = errorMessage;
        this.connote = connote;
    }

    public ConnoteDTO(Long connote) {
        this.connote = connote;
    }

    public ConnoteDTO(List<Long> connotes) {
        this.connoteList = connotes;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getConnote() {
        return connote;
    }

    public void setConnote(Long connote) {
        this.connote = connote;
    }

    public List<Long> getConnoteList() {
        return connoteList;
    }

    public void setConnoteList(List<Long> connoteList) {
        this.connoteList = connoteList;
    }
}
