package testFiles.API;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public class api {
    public static void main(String[] args){

        NumbersAPI(-1);
    }
    public static void NumbersAPI(int number) {
        HttpResponse<String> response;
        String url = "http://numbersapi.com/";
        if (number == -1)
            url += "random";
        else
            url += number;
        try {
            response = Unirest.get(url).asString();

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        if (response.getStatus() != 200) {
            System.out.println("error");
            return;
        }
        System.out.println("fact: " + response.getBody());
    }
    public static void loadNews(){
        try {
            //httpResponse - unirest
            HttpResponse<String> response = Unirest.get("https://newsapi.org/v2/top-headlines?country=il&apiKey=8868f50753c341079e6d1c669dbebbe2").asString();
            System.out.println(response.getBody());
            //object mapper - jackson
            ObjectMapper objectMapper = new ObjectMapper();
            NewsModel newsModel = objectMapper.readValue(response.getBody(), NewsModel.class);
            List<Article> dataItems = newsModel.getArticles();
            for (Article item : dataItems) {
                System.out.println("Author: " + item.getAuthor());
                System.out.println("title: " + item.getTitle());
                System.out.println();
            }
        } catch (UnirestException | JsonProcessingException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }
}
