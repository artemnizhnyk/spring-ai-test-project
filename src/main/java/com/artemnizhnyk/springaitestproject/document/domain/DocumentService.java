package com.artemnizhnyk.springaitestproject.document.domain;

import com.artemnizhnyk.springaitestproject.document.DocumentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
class DocumentService implements DocumentFacade {

    private final VectorStore pgVectorStore;

    @Override
    public List<Document> getSimilarDocuments(final String userPrompt) {
        return pgVectorStore.similaritySearch(SearchRequest.query(userPrompt).withTopK(2));
    }

    @Override
    public void addDocuments(final List<Document> documents) {
        pgVectorStore.add(documents);
        log.info("Added {} documents to the vector store", documents.size());
    }
}
