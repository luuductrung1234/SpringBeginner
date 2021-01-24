package com.learning.conference.conferencefirstbootapp.views;

import com.learning.conference.conferencefirstbootapp.seedworks.PagingParameters;
import org.springframework.data.domain.Sort;

public class FindSessionParameters extends PagingParameters {
    private String searchText;

    private int length;

    public FindSessionParameters(int page, int size, Sort.Direction direction, String... properties) {
        super(page, size, direction, properties);
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
