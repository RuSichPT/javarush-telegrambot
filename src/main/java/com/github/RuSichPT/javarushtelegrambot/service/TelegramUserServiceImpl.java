package com.github.RuSichPT.javarushtelegrambot.service;

import com.github.RuSichPT.javarushtelegrambot.repository.TelegramUserRepository;
import com.github.RuSichPT.javarushtelegrambot.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TelegramUserService}.
 */
@Service
public class TelegramUserServiceImpl implements TelegramUserService{

    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramUserRepository.findById(chatId);
    }
}
