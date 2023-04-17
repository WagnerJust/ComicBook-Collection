import axios from "axios";
import store from "../store/index.js"

const http = axios.create({
    baseURL: "http://localhost:9000/api",
    headers: {
        "Authorization": `Bearer ${store.state.token}`
    }    
});

export default{
    
    getComicsByCollectionId(collectionId){
        return http.get(`/collections/${collectionId}/comics`);
    },

    getComicById(comicId){
        return http.get(`/comics/${comicId}`)
    },

    getComicsByCharacterName(characterName){
        return http.get(`/comics/characters/real/${characterName}`);
    },

    getComicByUpc(upc){
        return http.get(`/comics/upc/${upc}`);
    },
    
    getComicsByCharacterAlias(characterAlias){
        return http.get(`/comics/characters/alias/${characterAlias}`);
    },

    getComicsByAuthor(authorName){
        return http.get(`/comics/author/${authorName}`);
    },

    getComicBySeries(seriesName){
        return http.get(`/comics/series/${seriesName}`);
    },

    addComicToCollection(collectionId, comicToAdd){
        return http.put(`/collections/${collectionId}/add`, comicToAdd);
    },

    addComic(comicToAdd){
        return http.post('/comics', comicToAdd);
    },

    removeComicFromCollection(collectionId,comicId){
        return http.delete(`/collections/${collectionId}/comics/${comicId}`);
    }


    


}