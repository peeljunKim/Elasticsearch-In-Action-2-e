package com.example.elastic.controller;

import com.example.elastic.bookDoc.BookDoc;
import com.example.elastic.repository.BookDocRepository;
import com.example.elastic.request.BookCreateRequestDto;
import com.example.elastic.request.BookUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
service 계층 생략
 */
@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    private BookDocRepository bookDocRepository;

    /*
    return book 도큐먼트 생성
     */
    @PostMapping("/create")
    public BookDoc createBook(@RequestBody BookCreateRequestDto requestDto) {
        BookDoc book = new BookDoc(
                requestDto.getId(),
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getPrice(),
                requestDto.isBestSeller());
        return bookDocRepository.save(book);
    }

    @GetMapping()
    public Page<BookDoc> findBooks() {
        return bookDocRepository.findAll(PageRequest.of(0, 10));
    }

    @GetMapping("/{id}")
    public BookDoc findBookById(@PathVariable String id) {
        return bookDocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));
    }

    @PutMapping("/{id}")
    public BookDoc updateBook(
            @PathVariable String id, @RequestBody BookUpdateRequestDto requestDto) {
        BookDoc findBook = bookDocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자"));

        BookDoc book = new BookDoc(
                id,
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getPrice(),
                requestDto.isBestSeller());

        return bookDocRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id){
        BookDoc findBook = bookDocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자"));
        bookDocRepository.delete(findBook);
    }
}
