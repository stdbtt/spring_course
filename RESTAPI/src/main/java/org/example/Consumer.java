package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Consumer {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("chat_id", "@test_rest_channel");
        jsonToSend.put("text", "Ты поц");

        //HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonToSend);
        String telegram = "https://api.telegram.org";
        String slash = "/";
        String bot = "bot";
        String token = "5410007331:AAFJ6c_zTtbfQtR2USiGxyduXEwytXT5OqU";
        String method = "sendMessage";
        String url = "https://reqres.in/api/users/2";
        ReqresResponse response = restTemplate.getForObject(url, ReqresResponse.class);
        System.out.println(response.getData().getFirst_name());

    }
}
