package iga.ac.iga_chatbot.service;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@BrowserCallable
@AnonymousAllowed
public class ChatService {
    private ChatClient chatClient;
    @Value("classpath:/prompts/prompt.st")
    private Resource promptResource;
    private VectorStore vectorStore;



    public ChatService(ChatClient.Builder builder,VectorStore vectorStore){
    this.chatClient= builder.build();
    this.vectorStore = vectorStore;

    }
    public String regChat(String question){
        PromptTemplate promptTemplate=new PromptTemplate(promptResource);
        List<Document> documents = vectorStore.similaritySearch(question);
        List<String> list = documents.stream().map(d -> d.getContent()).toList();
        Prompt prompt = promptTemplate.create(Map.of("context", list, "question", question));
        return chatClient.prompt(prompt).call().content();

    }

}
