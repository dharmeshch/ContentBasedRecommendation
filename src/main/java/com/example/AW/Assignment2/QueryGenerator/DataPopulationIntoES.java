package com.example.AW.Assignment2.QueryGenerator;


import com.example.AW.Assignment2.Model.ESModel;
import com.example.AW.Assignment2.Repositories.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class DataPopulationIntoES {

    @Autowired
    JavaRepository javaRepository;

    public void insertDataIntoES(File filePath) throws IOException {

        for(File entry : filePath.listFiles()){
            FileReader fileReader = new FileReader(entry);
            BufferedReader reader = new BufferedReader(fileReader);
            String content = null, line = null;
            while((line = reader.readLine())!=null){
                content += line;
                content += "/n";
            }
            ESModel esModel = new ESModel(entry.getName(), content);
            javaRepository.save(esModel);
        }

    }
}
