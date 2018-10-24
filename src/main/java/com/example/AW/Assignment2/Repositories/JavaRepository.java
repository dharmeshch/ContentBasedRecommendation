package com.example.AW.Assignment2.Repositories;

import com.example.AW.Assignment2.Model.ESModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JavaRepository extends ElasticsearchRepository<ESModel, String> {
}
