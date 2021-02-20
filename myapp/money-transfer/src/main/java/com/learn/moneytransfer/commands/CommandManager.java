package com.learn.moneytransfer.commands;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public abstract class CommandManager {
    public Object process(Object commandState) {
        Command command = createCommand();
        command.setState(commandState);
        return command.execute();
    }

    /**
     * Method Injection (demo)
     * <p>
     * Lookup method injection is the ability of the container to override methods on container-managed
     * beans and return the lookup result for another named bean in the container. The lookup
     * typically involves a prototype bean. The Spring Framework implements this method injection by
     * using bytecode generation from the CGLIB library to dynamically generate a subclass that
     * overrides the method.
     * </p>
     * @return Command
     */
    @Lookup
    protected abstract Command createCommand();
}
