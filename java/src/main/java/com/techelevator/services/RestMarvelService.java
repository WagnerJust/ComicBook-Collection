package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Comic;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RestMarvelService {

    String pubKey = "05a84f9109e4d58747820908b4af220b";
    String pvtKey = "964627a1c7a39f27cf1ea484b136e425134025ab";
    String ts = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    String hash = getMd5(ts+pvtKey+pubKey);
    String endUrl = "&ts="+ts+"&apikey="+pubKey+"&hash="+hash;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://gateway.marvel.com/v1/public/";


    public Comic getComicByUpc(String upc) throws JsonProcessingException {

        String resp = restTemplate.getForObject(apiUrl+"comics?upc="+upc+endUrl, String.class);
        JsonNode comicNode = new ObjectMapper().readTree(resp);
        return jsonComicMapper(comicNode.get("data").get("results").get(0));
    }

    public List<Comic> searchComicsBySeriesAndIssueNo(String series, String issueNo) throws JsonProcessingException {
        ArrayList<Comic> comics = new ArrayList<>();
        String resp = restTemplate.getForObject(apiUrl+"comics?titleStartsWith="+series+"&issueNumber="+issueNo+endUrl, String.class);
        JsonNode comicsNode = new ObjectMapper().readTree(resp).get("data").get("results");
        for (JsonNode comicNode : comicsNode){
            comics.add(jsonComicMapper(comicNode));
        }
        return comics;
    }




    private Comic jsonComicMapper(JsonNode comicNode){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Comic comic = new Comic();
        comic.setSeriesName(comicNode.get("title").textValue());
        JsonNode creators = (comicNode.get("creators").get("items"));
        for (JsonNode creator: creators){
            if (Objects.equals(creator.get("role").textValue(), "inker")){
                comic.setArtist(creator.get("name").textValue());
            }
            if (Objects.equals(creator.get("role").textValue(), "writer")){
                comic.setAuthor(creator.get("name").textValue());
            }
        }
        for (JsonNode date : comicNode.get("dates")){
            if (Objects.equals(date.get("type").textValue(), "focDate")){
                comic.setPublish_date(LocalDate.parse(date.get("date").textValue().substring(0, 10), formatter));
            }
        }
        comic.setUpc(comicNode.get("upc").textValue());
        comic.setIssueNumber(comicNode.get("issueNumber").intValue());
        comic.setImageURL(comicNode.get("images").get(0).get("path").textValue().substring(39));
        return comic;
    }


    // Copied this MD5 hashing function from https://www.geeksforgeeks.org/md5-hash-in-java/#
    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

