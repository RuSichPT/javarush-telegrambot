package com.github.RuSichPT.javarushtelegrambot.command;

import com.github.RuSichPT.javarushtelegrambot.repository.entity.TelegramUser;
import com.github.RuSichPT.javarushtelegrambot.service.SendBotMessageService;
import com.github.RuSichPT.javarushtelegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresent(
                user -> {
                    user.setActive(false);
                    telegramUserService.save(user);
                });

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}