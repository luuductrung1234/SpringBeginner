package com.learning.conference.conferencefirstbootapp.seedworks;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PagingParameters {
    private int page;
    private int size;
    private Sort.Direction direction;
    private String[] properties;

    public PagingParameters(int page, int size, Sort.Direction direction, String... properties) {
        this.page = page;
        this.size = size;
        this.direction = direction;
        this.properties = properties;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(page, size, Sort.by(direction, properties));
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
