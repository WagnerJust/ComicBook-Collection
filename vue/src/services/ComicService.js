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

    addComicToCollection(collectionId, comicToAdd){
        return http.put(`/collections/${collectionId}`, comicToAdd);
    },

    addComic(comicToAdd){
        return http.post('/comics', comicToAdd);
    }

}