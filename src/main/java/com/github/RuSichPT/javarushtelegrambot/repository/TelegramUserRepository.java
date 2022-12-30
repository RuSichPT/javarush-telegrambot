package com.github.RuSichPT.javarushtelegrambot.repository;

import com.github.RuSichPT.javarushtelegrambot.repository.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link Repository} for handling with {@link TelegramUser} entity.
 */
@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {
    List <TelegramUser> findAllByActiveTrue();
}