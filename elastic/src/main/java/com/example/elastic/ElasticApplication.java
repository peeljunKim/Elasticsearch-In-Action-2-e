package com.example.elastic;

//import com.example.elastic.service.HighLevelClientService;

import com.example.elastic.service.JavaApiClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class, args);
    }

//    --------------------------  High-Level_Client -----------------------
//    @Bean
//    public CommandLineRunner highLevelClientDemo(HighLevelClientService highLevelClientService) {
//        return args -> {
//            log.info("--- High-Level REST Client Demo ---");
//            try {
//                Map<String, Object> doc = Map.of("name", "Desk", "price", 300.0, "color", "brown");
//                String indexedId = highLevelClientService.indexDocument("products_high", "1", doc);
//                log.info("Indexed document ID: {}", indexedId);
//
//                Map<String, Object> retrievedDoc = highLevelClientService.getDocument("products_high", "3");
//                log.info("document: {}", retrievedDoc);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };
//    }


    //    --------------------------  java API Client -----------------------
    @Bean
    public CommandLineRunner javaApiClientDemo(JavaApiClientService javaApiClientService) {
        return args -> {
            System.out.println("--- Java API Client Demo ---");
            try {
                Map<String, Object> doc = Map.of("name", "Keyboard", "price", 150.0, "layout", "US");
                String indexedId = javaApiClientService.indexDocument("products_java_api", "1", doc);
                log.info("Indexed document ID: {}", indexedId);

                Map<String, Object> retrievedDoc = javaApiClientService.getDocument("products_java_api", "1");
                log.info("document: {}", retrievedDoc);

                Thread.sleep(1000); // 해당 코드를 추가하지 않으면, Refresh 주기 문제로 searchDocuments() 메소드가 제대로 실행이 안됨
                // 해결 방법으로 Refresh 주기를 변경할 수 있음

                List<Map> searchResults = javaApiClientService.searchDocuments("products_java_api", "Keyboard");
                log.info("Search results for 'Keyboard': {}", searchResults);

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
