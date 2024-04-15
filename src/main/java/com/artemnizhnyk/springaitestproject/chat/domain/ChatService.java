package com.artemnizhnyk.springaitestproject.chat.domain;

import com.artemnizhnyk.springaitestproject.chat.ChatFacade;
import com.artemnizhnyk.springaitestproject.chat.dto.MessageDto;
import com.artemnizhnyk.springaitestproject.chat.dto.MessageForm;
import com.artemnizhnyk.springaitestproject.chat.enumerated.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
class ChatService implements ChatFacade {

    private final ChatClient chatClient;

    private final List<MessageDto> messages = new ArrayList<>();

    @Override
    public List<MessageDto> getMessages() {
        return messages;
    }

    @Override
    public void sendMessage(MessageForm form) {
        Message userMessage = new UserMessage(form.content());
        Message systemMessage = getSystemMessage();
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        messages.add(new MessageDto(form.content(), MessageType.USER, LocalDateTime.now()));

        String result = chatClient.call(prompt)
                .getResult()
                .getOutput()
                .getContent();

        messages.add(new MessageDto(result, MessageType.SYSTEM, LocalDateTime.now()));
    }

    private Message getSystemMessage() {
        String systemPrompt = """
                You are an assistant which answers to customer's questions.
                I'm adding to the context messages were sent from the previus conversation with the user: {messages}
                """;
        return new SystemPromptTemplate(systemPrompt)
                .createMessage(Map.of(
                        "messages", messages
                ));
    }

}
