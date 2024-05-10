package com.youneskarir.demo.service;

import com.youneskarir.demo.dto.BookRequest;
import com.youneskarir.demo.model.Book;
import com.youneskarir.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book save(BookRequest request) {
        var book = Book.builder()
                .id(request.getId())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .build();
        repository.save(book);
        return book;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }
}
