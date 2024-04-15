package com.artemnizhnyk.springaitestproject.chat;

import com.artemnizhnyk.springaitestproject.chat.dto.MessageDto;
import com.artemnizhnyk.springaitestproject.chat.dto.MessageForm;

import java.util.List;

public interface ChatFacade {

    List<MessageDto> getMessages();

    void sendMessage(MessageForm form);
}
