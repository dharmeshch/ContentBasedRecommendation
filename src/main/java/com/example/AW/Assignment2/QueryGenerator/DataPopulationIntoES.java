package com.example.AW.Assignment2.QueryGenerator;


import com.example.AW.Assignment2.Model.ESModel;
import com.example.AW.Assignment2.Repositories.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Repository
public class DataPopulationIntoES {

    @Autowired
    JavaRepository javaRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public void insertDataIntoES(File filePath) throws IOException {

        for(File entry : filePath.listFiles()){
            FileReader fileReader = new FileReader(entry);
            BufferedReader reader = new BufferedReader(fileReader);
            String content = null, line = null;
            while((line = reader.readLine())!=null){
                content += line;
                content += "/n";
            }
            ESModel esModel = new ESModel(entry.getName().split(".txt")[0], content);
            javaRepository.save(esModel);
        }

    }
//
//    public void createQueryForES(String query){
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQuery(query))
//                .withFields("content")
//                .withFields("fuzzines","auto")
//                .build();
//        searchQuery.get
//    }

    public List<ESModel> createQueryForES(String query){
        Criteria c = new Criteria("content").fuzzy(query);
         return elasticsearchTemplate.queryForList(new CriteriaQuery(c), ESModel.class);
    }
}
