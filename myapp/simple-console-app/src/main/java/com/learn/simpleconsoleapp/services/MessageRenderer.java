package com.learn.simpleconsoleapp.services;

public interface MessageRenderer {
    /**
     * render a message come from {@link MessageProvider}
     * @throws java.lang.RuntimeException
     */
    void render();

    MessageProvider getMessageProvider();
}
