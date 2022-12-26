package com.github.RuSichPT.javarushtelegrambot.bot;

import com.github.RuSichPT.javarushtelegrambot.command.CommandContainer;
import com.github.RuSichPT.javarushtelegrambot.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

    public JavarushTelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
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
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
