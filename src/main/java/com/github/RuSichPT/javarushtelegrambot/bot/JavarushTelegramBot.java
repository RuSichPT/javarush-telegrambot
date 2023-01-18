package com.github.RuSichPT.javarushtelegrambot.bot;

import com.github.RuSichPT.javarushtelegrambot.command.CommandContainer;
import com.github.RuSichPT.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.RuSichPT.javarushtelegrambot.service.GroupSubService;
import com.github.RuSichPT.javarushtelegrambot.service.SendBotMessageServiceImpl;
import com.github.RuSichPT.javarushtelegrambot.service.StatisticsService;
import com.github.RuSichPT.javarushtelegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.github.RuSichPT.javarushtelegrambot.command.CommandName.NO;

/**
 * Telegrambot.
 */
@Component
public class JavarushTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Autowired
    public JavarushTelegramBot(TelegramUserService telegramUserService, JavaRushGroupClient javaRushGroupClient, StatisticsService statisticsService,
                               GroupSubService groupSubService, @Value("#{'${bot.admins}'.split(',')}") List<String> admins) {
        this.commandContainer = new CommandContainer(
                new SendBotMessageServiceImpl(this),telegramUserService,
                javaRushGroupClient, groupSubService, statisticsService, admins);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText())
        {
            String message = update.getMessage().getText().trim();

            if (message.startsWith(COMMAND_PREFIX))
            {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier, update.getMessage().getFrom().getUserName()).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(),update.getMessage().getFrom().getUserName()).execute(update);
            }
        }
    }
}
