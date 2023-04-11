import axios from 'axios'
import CryptoJS from 'crypto-js';

const http = axios.create({
    baseURL: 'http://gateway.marvel.com/v1/public/'
});


var pubkey = "76d0e7cf435efb6b98077dbeb60e1a7e";
var pvtkey = "2354076cc2f715c89acf505c8eb541c711a7a3ff";
var ts = new Date().getTime();
var apikey = pubkey;
var urladdition = ts+pubkey+pvtkey;
var hash = CryptoJS.MD5(urladdition).toString();
var endURL = "&ts="+ts+"&apikey="+apikey+"&hash="+hash;

export default{

 
    //COMIC REQUESTS
    searchComicByName(title){
        return http.get(`comics?titleStartsWith=${title}${endURL}`)
    },

    searchComicByIssue(title, issue){
        return http.get(`comics?titleStartsWith=${title}&issueNumber=${issue}${endURL}`)
    },

    getComidByCharacter(characterId){
        return http.get(`characters/${characterId}/comics?${endURL}`)
    },



    //CHARACTER REQUESTS
    getCharacterByName(characterName){
        return http.get(`characters?nameStartsWith=${characterName}${endURL}`)
    },
    
    getCharacterById(characterId){
        return http.get(`characters/${characterId}?${endURL}`)
    }
}