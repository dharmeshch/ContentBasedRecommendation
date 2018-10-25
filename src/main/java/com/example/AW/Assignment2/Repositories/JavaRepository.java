package com.example.AW.Assignment2.Repositories;

import com.example.AW.Assignment2.Model.ESModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface JavaRepository extends ElasticsearchRepository<ESModel, String> {

    public List<ESModel> findAllByContent(String content);

    @Query("{\"query\": {\"multi_match\": {\"query\": \"?\",\"fields\": [\"content\"],\"fuzziness\": \"AUTO\"}}}")
    public List<ESModel> findByContentType(String content);
}
