import axios from "axios";

const http = axios.create({
    baseURL:"http://localhost:9000/api"

})

export default{
    
    geComicsByCollectionId(collectionId){
        http.get(`/collections/${collectionId}/comics`);
    },

    getComicById(comicId){
        http.get(`/comics/${comicId}`)
    }

}