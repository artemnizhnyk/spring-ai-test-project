package com.artemnizhnyk.springaitestproject.document.domain;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
class VectorStoreConfig {

    @Bean
    VectorStore pgVectorStore(JdbcTemplate jdbcTemplate, EmbeddingClient embeddingClient) {
        return new PgVectorStore(jdbcTemplate, embeddingClient);
    }
}
