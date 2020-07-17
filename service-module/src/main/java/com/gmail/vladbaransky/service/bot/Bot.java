package com.gmail.vladbaransky.service.bot;

import com.gmail.vladbaransky.service.BotService;
import com.gmail.vladbaransky.service.model.CityDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Profile("!test")
@ConfigurationProperties(prefix = "telegram")
public class Bot extends TelegramLongPollingBot {
    private final BotService botService;
    private String botUsername;
    private String botToken;

    public Bot(BotService botService) {
        this.botService = botService;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            CityDTO city = botService.getCityByName(update.getMessage().getText());
            try {
                execute(new SendMessage(chat_id, city.getInfo()));
            } catch (TelegramApiException e) {
                e.getMessage();
            }
        }
    }
}

