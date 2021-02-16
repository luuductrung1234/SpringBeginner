package com.learn.simpleconsoleapp.beans;

import org.springframework.stereotype.Component;

@Component("lyricHolder")
public class LyricHolder implements ContentHolder {
    private String value = "'You be the DJ, I'll be the driver'";
    @Override public String toString() {
        return "LyricHolder: { " + value + "}";
    }
}
