package com.example.elastic.repository;

import com.example.elastic.bookDoc.BookDoc;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookDocRepository extends ElasticsearchRepository<BookDoc, String> {

    // @Query 사용
    @Query("{\"match\":{\"title\":\"?0\"}}")
    List<BookDoc> matchByTitle(String title);
}
