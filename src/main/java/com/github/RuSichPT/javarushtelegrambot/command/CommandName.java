package com.github.RuSichPT.javarushtelegrambot.command;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STAT("/stat"),
    NO(""),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub");


    private final String commandName;

    CommandName(String command) {
        this.commandName = command;
    }

    public String getCommandName() {
        return commandName;
    }
}
