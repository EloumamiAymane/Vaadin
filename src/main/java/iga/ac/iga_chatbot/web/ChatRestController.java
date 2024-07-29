package iga.ac.iga_chatbot.web;

import iga.ac.iga_chatbot.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatRestController {
    private ChatService chatService;


    @GetMapping("/ask")
    public String ask(String question){
        return  chatService.regChat(question);
    }
}
