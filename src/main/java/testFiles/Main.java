package testFiles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args)   {

        scrapMoviesNamesBYID();

    }
    public static void scrapMoviesNamesBYID(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.imdb.com").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String title = document.title();
        Elements elements = document.getElementsByClass("ipc-sub-grid ipc-sub-grid--page-span-3 ipc-sub-grid--nowrap ipc-shoveler__grid");
        System.out.println(title);
        System.out.println(elements.size());
        for(Element element: elements){
            System.out.println(element.text());
        }
//        String[] lines = elements.get(0).text().split("\n");
//        List<String> names = new ArrayList<>();
//        for (int i = 6; i < 21; i++) {
//            if (i == 12) continue;
//            if (i > 10) names.add(lines[i].substring(3, 27));
//            else names.add(lines[i].substring(4, 27));
//        }
//        System.out.println("names: \n" + names);

    }
    public static void scrapTeamNamesByTag(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.rsssf.org/tablesi/isra2022.html").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String title = document.title();
        List<Element> elements = document.getElementsByTag("pre");
        System.out.println(title);
        System.out.println(elements.size());
        String[] lines = elements.get(0).text().split("\n");
        List<String> names = new ArrayList<>();
        for (int i = 6; i < 21; i++) {
            if (i == 12) continue;
            if (i > 10) names.add(lines[i].substring(3, 27));
            else names.add(lines[i].substring(4, 27));
        }
        System.out.println("names: \n" + names);
    }

}