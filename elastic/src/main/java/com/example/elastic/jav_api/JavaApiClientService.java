//package com.example.elastic.service;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.core.GetResponse;
//import co.elastic.clients.elasticsearch.core.IndexResponse;
//import co.elastic.clients.elasticsearch.core.SearchResponse;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import co.elastic.clients.elasticsearch.indices.RefreshRequest;
//import co.elastic.clients.elasticsearch.indices.RefreshResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@Slf4j
//public class JavaApiClientService {
//
//    private final ElasticsearchClient esClient;
//
//    @Autowired
//    public JavaApiClientService(ElasticsearchClient esClient) {
//        this.esClient = esClient;
//    }
//
//    // 문서 인덱싱 (저장)
//    public String indexDocument(String indexName, String id, Map<String, Object> document) throws IOException {
//        IndexResponse response = esClient.index(i -> i
//                .index(indexName)
//                .id(id)
//                .document(document)
//        );
//        return response.id();
//    }
//
//    // 문서 조회
//    public Map<String, Object> getDocument(String indexName, String id) throws IOException {
//        GetResponse<Map> response = esClient.get(g -> g
//                        .index(indexName)
//                        .id(id),
//                Map.class // Map으로 매핑
//        );
//        return response.source();
//    }
//
//    // 문서 검색 (간단한 match all 쿼리)
//    public List<Map> searchDocuments(String indexName, String query) throws IOException {
//        SearchResponse<Map> response = esClient.search(s -> s
//                        .index(indexName)
//                        .query(q -> q
//                                .match(m -> m
//                                        .field("name") // 'name' 필드에서 쿼리
//                                        .query(query)
//                                )
//                        ),
//                Map.class  // Map으로 매핑
//        );
//
//        return response.hits().hits().stream()
//                .map(Hit::source)
//                .toList();
//    }
//}
