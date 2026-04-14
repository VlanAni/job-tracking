package ru.vk.education.job.services;

import java.util.Arrays;

public class UserInput {

    private final String[] tokens;

    public UserInput(String command) {
        tokens = command.split(" ");
    }

    public String getCommand() {
        return tokens[0];
    }

    public String[] getArgs() {
        return Arrays.copyOfRange(tokens, 1, tokens.length);
    }
}
