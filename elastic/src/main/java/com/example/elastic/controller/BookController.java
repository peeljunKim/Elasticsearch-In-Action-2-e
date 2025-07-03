package com.example.elastic.controller;

import com.example.elastic.bookDoc.BookDoc;
import com.example.elastic.repository.BookDocRepository;
import com.example.elastic.request.BookCreateRequestDto;
import com.example.elastic.request.BookUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
service 계층 생략
 */
@RestController
@AllArgsConstructor
@RequestMapping("/book")
@Slf4j
public class BookController {

    private BookDocRepository bookDocRepository;
    private final ElasticsearchOperations elasticsearchOperations;

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


    // @Query 이용해서 match 사용
    @PostMapping("/query/{title}")
    public Collection<BookDoc> matchByTile (@PathVariable String title){
        return bookDocRepository.matchByTitle(title);
    }

    // Criteria 사용
    @PostMapping("/criteria/{title}")
    public Collection<BookDoc> CriteriaByCriteria (@PathVariable String title){

        // title 필드에 대한 match 쿼리를 생성
        Criteria criteria = Criteria.where("title").matches(title);

        // 쿼리를 생성
        Query query = new CriteriaQuery(criteria);

        // elasticsearchOperations를 사용하여 쿼리를 실행하고 SearchHits를 받습니다
        SearchHits<BookDoc> searchHits = elasticsearchOperations.search(query, BookDoc.class);

        // SearchHits에서 실제 BookDoc 객체 리스트를 추출하여 반환
        List<BookDoc> books = searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        log.info("검색된 책들 = {} 권", books.size());
        return books;
    }


}
