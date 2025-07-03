//package com.example.elastic.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.xcontent.XContentType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Service
//public class HighLevelClientService {
//
//    private final RestHighLevelClient client;
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    public HighLevelClientService(RestHighLevelClient client, ObjectMapper objectMapper) {
//        this.client = client;
//        this.objectMapper = objectMapper;
//    }
//
//    public String indexDocument(String indexName, String id, Map<String, Object> document) throws IOException {
//        IndexRequest request = new IndexRequest(indexName)
//                .id(id)
//                .source(objectMapper.writeValueAsString(document), XContentType.JSON); // JSON 문자열로 소스 지정
//        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//        return response.getId();
//    }
//
//    public Map<String, Object> getDocument(String indexName, String id) throws IOException {
//        GetRequest getRequest = new GetRequest(indexName, id);
//        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//        if (getResponse.isExists()) {
//            return getResponse.getSourceAsMap();
//        }
//        return null;
//    }
//}