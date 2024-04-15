package com.artemnizhnyk.springaitestproject.chat.dto;

import com.artemnizhnyk.springaitestproject.chat.enumerated.MessageType;

import java.time.LocalDateTime;

public record MessageDto(String content,
                         MessageType type,
                         LocalDateTime timestamp
                         ) {
}
