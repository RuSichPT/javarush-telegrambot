package com.github.RuSichPT.javarushtelegrambot.command;

import com.github.RuSichPT.javarushtelegrambot.service.SendBotMessageService;
import com.github.RuSichPT.javarushtelegrambot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import static com.github.RuSichPT.javarushtelegrambot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier)
    {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
