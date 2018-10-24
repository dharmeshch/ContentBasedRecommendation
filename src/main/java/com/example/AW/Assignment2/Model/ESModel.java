package com.example.AW.Assignment2.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

@Document(indexName = "java")
public class ESModel {

    @Id
    String id;

    String title;

    String content;


    public ESModel(String title, String content){
        this.title  = title;
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
