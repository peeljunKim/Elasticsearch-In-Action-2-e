package com.example.elastic.request;

import lombok.Getter;

@Getter
public class BookCreateRequestDto {
    private String id;
    private String title;
    private String author;
    private Long price;
    private boolean bestSeller;

}
