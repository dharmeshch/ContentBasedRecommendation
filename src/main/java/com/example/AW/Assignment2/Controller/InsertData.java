package com.example.AW.Assignment2.Controller;

import com.example.AW.Assignment2.QueryGenerator.DataPopulationIntoES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class InsertData {

    @Autowired
    DataPopulationIntoES data;

    File filePath =  new File("/Users/dharmeshch/Desktop/Crawler/");

    @RequestMapping(value = "/insertDataIntoES", method = RequestMethod.POST)
    public void insertDataIntoES() throws IOException {
        data.insertDataIntoES(filePath);
    }
}
