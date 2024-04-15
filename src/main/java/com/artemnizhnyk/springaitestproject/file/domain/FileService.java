package com.artemnizhnyk.springaitestproject.file.domain;

import com.artemnizhnyk.springaitestproject.document.DocumentFacade;
import com.artemnizhnyk.springaitestproject.file.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class FileService implements FileFacade {

    private final DocumentFacade documentFacade;

    @Value("classpath:firma.pdf")
    private Resource resource;

    @Override
    public void loadFileToVectorStore() {
        PagePdfDocumentReader documentReader = new PagePdfDocumentReader(resource, createReaderConfig());

        TextSplitter textSplitter = new TokenTextSplitter();
        List<Document> documents = textSplitter.apply(documentReader.get());

        documentFacade.addDocuments(documents);
    }

    private PdfDocumentReaderConfig createReaderConfig() {
        return PdfDocumentReaderConfig.builder()
                .withPageTopMargin(0)
                .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder()
                        .withNumberOfBottomTextLinesToDelete(0)
                        .build())
                .withPagesPerDocument(1)
                .build();
    }
}
