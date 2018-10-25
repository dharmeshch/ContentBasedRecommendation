package com.example.AW.Assignment2.Controller;

import com.example.AW.Assignment2.Model.ESModel;
import com.example.AW.Assignment2.QueryGenerator.DataPopulationIntoES;
import com.example.AW.Assignment2.Repositories.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class InsertData {

    @Autowired
    DataPopulationIntoES data;

    @Autowired
    JavaRepository javaRepository;

    File filePath =  new File("/Users/dharmeshch/Desktop/Crawler/");
    File filePath1 = new File("/Users/dharmeshch/Desktop/Oracle/");

    @RequestMapping(value = "/insertDataIntoES", method = RequestMethod.POST)
    public void insertDataIntoES() throws IOException {
        data.insertDataIntoES(filePath1);
    }

    @RequestMapping(value = "/getIndexedData", method = RequestMethod.GET)
    public List<ESModel> getIndexedData(@RequestParam(value = "value") String value){
//        return javaRepository.findAllByContent(value);
        return javaRepository.findByContentType(value);
    }
}
