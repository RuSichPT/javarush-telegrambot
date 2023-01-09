package com.github.RuSichPT.javarushtelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.RuSichPT.javarushtelegrambot.command.CommandName.START;
import static com.github.RuSichPT.javarushtelegrambot.command.StartCommand.START_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class StartCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService, telegramUserService);
    }
}
