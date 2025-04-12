package com.chat.realtimemessage.controller;

import com.chat.realtimemessage.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @MessageMapping("sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage chatMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    @GetMapping
    public String chat(){
        return "chat";
    }


}
