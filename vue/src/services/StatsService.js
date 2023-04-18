import axios from "axios";
import store from "../store/index.js"

const http = axios.create({
    baseURL: "http://localhost:9000/api/stats",
    headers: {
        "Authorization": `Bearer ${store.state.token}`
    }    
});

export default{

    getCharactersStats(collectionId){
        return http.get(`/${collectionId}/character`);
    },
    getAuthorStats(collectionId){
        return http.get(`/${collectionId}/author`);
    },
    getSeriesStats(collectionId){
        return http.get(`/${collectionId}/series`);
    },

    getSiteStats(){
        return http.get('/');
    }
}