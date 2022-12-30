package com.github.RuSichPT.javarushtelegrambot.command;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STAT("/stat"),
    NO("");

    private final String commandName;

    CommandName(String command) {
        this.commandName = command;
    }

    public String getCommandName() {
        return commandName;
    }
}
