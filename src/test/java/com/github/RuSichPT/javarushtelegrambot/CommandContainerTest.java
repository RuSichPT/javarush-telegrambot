package com.github.RuSichPT.javarushtelegrambot;

import com.github.RuSichPT.javarushtelegrambot.command.Command;
import com.github.RuSichPT.javarushtelegrambot.command.CommandContainer;
import com.github.RuSichPT.javarushtelegrambot.command.CommandName;
import com.github.RuSichPT.javarushtelegrambot.command.UnknownCommand;
import com.github.RuSichPT.javarushtelegrambot.service.SendBotMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init(){
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        commandContainer = new CommandContainer(sendBotMessageService);
    }

    @Test
    public void shouldGetAllTheExistingCommands(){
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(CommandName->{
                    Command command = commandContainer.retrieveCommand(CommandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/sdfgsasf";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
