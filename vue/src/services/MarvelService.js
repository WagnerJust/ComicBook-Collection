import axios from 'axios'
import store from '../store/index.js'

const http = axios.create({
    baseURL: "http://localhost:9000/marvel/",
    headers: {
        "Authorization": `Bearer ${store.state.token}`
    }    
});


export default{


    //COMIC REQUESTS
    searchComicByTitle(title){
        return http.get(`comics/title/${title}`)
    },

    searchComicBySeries(series){
        return http.get(`comics/series/${series}`)
    },

    searchComicByAuthor(name){
        return http.get(`comics/author/${name}`)
    },

    searchComicByArtist(name){
        return http.get(`comics/artist/${name}`)
    },


    searchComicByIssue(issue){
        return http.get(`comics/issue/${issue}`)
    },

    searchComicByCharacter(characterId){
        return http.get(`characters/${characterId}/comics`)
    },

    

    // serachComicByDateRange(date1, date2){
    //     //dates should be YYYY-MM-DD
    //     return http.get(`comics/dateRange?date1=${date1}&date2=${date2}`)
    // },




    //CHARACTER REQUESTS
    getCharacterIdByName(characterName){
        return http.get(`characters/name/${characterName}`)
    },
    
    getCharacterById(characterId){
        return http.get(`characters/${characterId}`)
    },

    getCharactersInComic(comicId){
        return http.get(`/comics/${comicId}/characters}`)
    }


}