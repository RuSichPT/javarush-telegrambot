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
    LIST_GROUP_SUB("/listgroupsub"),
    DELETE_GROUP_SUB("/deletegroupsub"),
    ADMIN_HELP("/ahelp");


    private final String commandName;

    CommandName(String command) {
        this.commandName = command;
    }

    public String getCommandName() {
        return commandName;
    }
}
