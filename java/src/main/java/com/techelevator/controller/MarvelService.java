package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Comic;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Component
public class MarvelService {

    String pubKey = "05a84f9109e4d58747820908b4af220b";
    String pvtKey = "964627a1c7a39f27cf1ea484b136e425134025ab";
    String ts = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    String hash = getMd5(ts+pvtKey+pubKey);
    String endUrl = "&ts="+ts+"&apikey="+pubKey+"&hash="+hash;
    private RestTemplate restTemplate = new RestTemplate();
    private String apiUrl = "http://gateway.marvel.com/v1/public/";


    public Comic getComicByUpc(@PathVariable String upc) throws JsonProcessingException {

        String resp = restTemplate.getForObject(apiUrl+"comics?upc="+upc+endUrl, String.class);
        JsonNode comicNode = new ObjectMapper().readTree(resp);
        Comic comic = new Comic();
        comic.setSeriesName(comicNode.get("data").get("results").get(0).get("title").textValue());

        return comic;

//        Comic comic = new ObjectMapper()
//                .readerFor(Comic.class)
//                .readValue(resp);
//        return comic;

        //        JsonParser springParser = JsonParserFactory.getJsonParser();
//        Map<String, Object> map = springParser.parseMap(resp);
//        Map<String, Object> map2 = springParser.parseMap(map.get("data").toString());
//        Map<String, Object> map3 = springParser.parseMap(map2.get("results").toString());
//        return map3.get("title").toString();


    }


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
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

