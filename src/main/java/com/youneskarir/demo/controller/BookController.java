package com.youneskarir.demo.controller;

import com.youneskarir.demo.model.Book;
import com.youneskarir.demo.dto.BookRequest;
import com.youneskarir.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public Book save(
            @RequestBody BookRequest request
    ) {
        return service.save(request);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(service.findAll());
    }
}
