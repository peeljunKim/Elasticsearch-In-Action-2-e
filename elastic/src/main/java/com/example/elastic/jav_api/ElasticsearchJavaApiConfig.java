//package com.example.elastic.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticsearchJavaApiConfig {
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        // low-level REST client 생성
//        RestClient restClient = RestClient.builder(
//            new HttpHost("localhost", 9200, "http")
//        ).build();
//
//        // Jackson 매퍼를 사용
//        ElasticsearchTransport transport = new RestClientTransport(
//            restClient, new JacksonJsonpMapper());
//
//        // API 클라이언트를 생성
//        return new ElasticsearchClient(transport);
//    }
//}