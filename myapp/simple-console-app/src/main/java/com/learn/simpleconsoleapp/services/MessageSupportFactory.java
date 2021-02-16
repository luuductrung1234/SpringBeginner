package com.learn.simpleconsoleapp.services;

import java.util.Properties;

/**
 * Usage:
 * <pre>
 * {@code
 * public class App {
 *  public static void main(String[] args) {
 *      MessageRenderer messageRenderer = MessageSupportFactory.getInstance().getMessageRenderer();
 *      messageRenderer.render();
 *  }
 * }
 * }
 * </pre>
 *
 * @deprecated using Spring Framework to manage instances of {@link MessageRenderer}
 * and {@link MessageProvider}
 */
@Deprecated
public class MessageSupportFactory {
    private final String MESSAGE_RENDER_CLASS_PROPERTY_NAME = "message.renderer.class";
    private final String MESSAGE_PROVIDER_CLASS_PROPERTY_NAME = "message.provider.class";

    private static MessageSupportFactory instance;

    private Properties props;
    private MessageProvider provider;
    private MessageRenderer renderer;

    static {
        instance = new MessageSupportFactory();
    }

    private MessageSupportFactory() {
        props = new Properties();

        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("msf.properties"));

            Class<?> providerClass = Class.forName(props.getProperty(this.MESSAGE_PROVIDER_CLASS_PROPERTY_NAME));
            Class<?> rendererClass = Class.forName(props.getProperty(this.MESSAGE_RENDER_CLASS_PROPERTY_NAME));

            provider = (MessageProvider) providerClass.getDeclaredConstructor().newInstance();
            renderer = (MessageRenderer) rendererClass.getDeclaredConstructor(
                    providerClass.getInterfaces()).newInstance(provider);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }
}
