package com.example.elastic.bookDoc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

/*
Spring Data Elasticsearch의 @Document는 Elasticsearch에게 해당 Doc 존재 유부를 물어본다
인덱스가 존재하지 않으면 아래 코드대로 인덱스를 생성
이미 존재하면 아무런 일도 일어나지 않는다.
 */
@Document(indexName = "book")
@Setter
@Getter
@AllArgsConstructor
@Builder
public class BookDoc {
    @Id
    private String id;

    @Field(type = Keyword)
    private String title;

    @Field(type = Keyword)
    private String author;

    @Field(type = Long)
    private Long price;

    @Field(type = Boolean)
    private boolean bestSeller;

}
