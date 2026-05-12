package ru.vk.education.job.cmd;

import ru.vk.education.job.exceptions.UnsupportedCommandException;

import java.util.Map;

public class CommandRouter {
    private final Map<String, Command<?>> commandMap;

    public CommandRouter(Map<String, Command<?>> commandMap) {
        this.commandMap = commandMap;
    }

    public Command<?> getCommand(String commandName) throws UnsupportedCommandException {
        Command<?> cmd =  commandMap.get(commandName);
        if (cmd == null) {
            throw new UnsupportedCommandException("unsupported command" + commandName);
        } else {
            return cmd;
        }
    }
}
