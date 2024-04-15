package com.artemnizhnyk.springaitestproject.document;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DocumentFacade {

    List<Document> getSimilarDocuments(String userPrompt);

    void addDocuments(List<Document> documents);
}
