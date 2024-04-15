package com.artemnizhnyk.springaitestproject.file.controller;

import com.artemnizhnyk.springaitestproject.file.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/ai/file")
@RestController
public class FileController {

    private final FileFacade fileFacade;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/load")
    void loadFileToVectorStore() {
        fileFacade.loadFileToVectorStore();
    }
}
