package com.github.RuSichPT.javarushtelegrambot.command;

import com.github.RuSichPT.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.RuSichPT.javarushtelegrambot.service.GroupSubService;
import com.github.RuSichPT.javarushtelegrambot.service.SendBotMessageService;
import com.github.RuSichPT.javarushtelegrambot.service.StatisticsService;
import com.github.RuSichPT.javarushtelegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static java.util.Collections.singletonList;

public class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init(){
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticsService statisticsService = Mockito.mock(StatisticsService.class);
        commandContainer = new CommandContainer(sendBotMessageService,
                telegramUserService,
                javaRushGroupClient,
                groupSubService,
                statisticsService,
                singletonList("username"));
    }

    @Test
    public void shouldGetAllTheExistingCommands(){
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(CommandName->{
                    Command command = commandContainer.retrieveCommand(CommandName.getCommandName(),"username");
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/sdfgsasf";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand,"username");

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
