import axios from "axios";

const http = axios.create({
    baseURL:"http://localhost:9000/api"

})

export default{
    
    geComicsByCollectionId(collectionId){
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