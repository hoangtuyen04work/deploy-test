package io.github.hoangtuyen04work.social_backend.controller;


import io.github.hoangtuyen04work.social_backend.services.ConversationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class ConservationController {
    SimpMessagingTemplate simpMessagingTemplate;
    ConversationService conservation;
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public void createChat(@Payload MessageRequest request){
        MessageResponse response = chatService.sendMessage(request);
        simpMessagingTemplate.convertAndSend("/topic/messages" + request.getChatId(), response);
    }

    @GetMapping("/chat/{userid1}/{userid2}")
    public ChatResponse getChat(@PathVariable String userid1, @PathVariable String userid2){
        return chatService.getChat(userid1, userid2);
    }
}