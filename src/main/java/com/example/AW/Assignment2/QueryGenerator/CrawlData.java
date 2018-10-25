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
                crawlURLJava(ele.attr("abs:href"));
            }
        }

    }

    public void crawlURLJava(String url) throws IOException, BoilerpipeProcessingException {

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

    public void crawlURLOracle(String url) throws IOException, BoilerpipeProcessingException {

        Document oracledocs= Jsoup.connect(url).get();
        Elements oracleqlinks= oracledocs.select("div#PageContent>ul>li>ul>li>a[href],div#PageContent>ul>li>ul>li>ul>li>a[href]");

        //parsing title of page url..
        int a = 0;
        String[] titles = new String[oracleqlinks.size()];
        for (Element links : oracleqlinks) {
            String[] splits = links.attr("abs:href").split("/");
            titles[a] = splits[splits.length - 1];
            titles[a] = titles[a].replace(".html", "");
            a++;
        }

        //creating text article document for each link
        int parts = 0;

        for (Element links : oracleqlinks) {

            Document document = Jsoup.connect(links.attr("abs:href")).get();

            document.select("div#MainFlow>div.PrintHeaders,div#MainFlow>div.BreadCrumbs,div#MainFlow>div.NavBit,div#MainFlow>div.PageTitle")
                    .remove();

            Elements page = document.select("div#MainFlow>*");

            List<String> pageParts = Arrays.asList(page.toString().replaceAll("../../", "https://docs.oracle.com/javase/tutorial/").split("<h[0-9]>.*?h[0-9]>|<p><br></p>"));

            int totalPageParts=pageParts.size();

            File Library = new File("/Users/dharmeshch/Desktop/Crawler/Oracle");

            //for each link...process
            for (int x = 0; x < totalPageParts; x++) {

                if ( pageParts.get(x).split("\\s+").length > 300) {

                    pageParts.get(x).replaceAll("../../", "https://docs.oracle.com/javase/tutorial/");


                    //Fetching article through boilerpipe library with Default extractor

                    String article = DefaultExtractor.INSTANCE.getText(pageParts.get(x));

                    PrintWriter writer = new PrintWriter(Library + "/" + titles[parts] + "_part" + x + "_oracle.txt",
                            "UTF-8");
                    writer.println(article);
                    writer.close();
                }
            }
            parts++;
        }
    }
}
