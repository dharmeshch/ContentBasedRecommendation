package com.example.AW.Assignment2.Controller;


import com.example.AW.Assignment2.QueryGenerator.CrawlData;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CrawlerController {

    @Autowired
    CrawlData crawlData;
    @RequestMapping(value = "/crawlJavaWikiBooks", method = RequestMethod.POST)
    public String crawlJavaWikiBooks() throws IOException, BoilerpipeProcessingException {

        crawlData.crawlData("https://en.wikibooks.org/wiki/Java_Programming");
        return "check";
    }

    @RequestMapping(value = "/crawlOracleData", method = RequestMethod.POST)
    public String crawlOracleData() throws IOException, BoilerpipeProcessingException {

        crawlData.crawlURLOracle("https://docs.oracle.com/javase/tutorial/java/TOC.html");
        return "check1";
    }
}
