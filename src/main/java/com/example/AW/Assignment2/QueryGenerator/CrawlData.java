package com.example.AW.Assignment2.QueryGenerator;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.DefaultExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

@Repository
public class CrawlData {

    public void crawlData(String url) throws IOException, BoilerpipeProcessingException {

        Document document = Jsoup.connect(url).get();

        for(Element ele : document.select("a[href]")){
            if(ele.attr("href").contains("Print_version")){
                continue;
            }

            if(ele.attr("abs:href").contains("wiki/Java_Programming")){
                crawlURL(ele.attr("abs:href"));
            }
        }

    }

    public void crawlURL(String url) throws IOException, BoilerpipeProcessingException {

        String title = url.replace("https://en.wikibooks.org/wiki/Java_Programming/","");
        String titleArray[] = title.split("/");
        title=titleArray[titleArray.length-1];

        Document doc = Jsoup.connect(url).get();
        doc.select("div#mw-content-text>table.wikitable.noprint").remove();
        doc.select("div#mw-content-text>table.wikitable").remove();
        doc.select("h2>span.mw-editsection").remove();
        Elements page = doc.select("div#mw-content-text>*");

        List<String> pageparts = Arrays.asList(page.toString().replaceAll("^.*?<h[0-3]>", "").split("<h[0-9]>.*?h[0-9]>|<p><br></p>"));

        int p=0;
        for(String s: pageparts) {
            if(s.split("\\s+").length > 300) {
                p++;
                File file = new File("/Users/dharmeshch/Desktop/Crawler/"+ title +p+ "_wikibook.txt");

                PrintWriter writer = new PrintWriter(file);
                String article = DefaultExtractor.INSTANCE.getText(s);
                writer.println(article);
                writer.close();
            }

        }
    }
}
