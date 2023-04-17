import axios from "axios";
import store from "../store/index.js"

const http = axios.create({
    baseURL: "http://localhost:9000/api/stats",
    headers: {
        "Authorization": `Bearer ${store.state.token}`
    }    
});

export default{

    getStatsForCollection(collectionId){
        return http.get(`/${collectionId}`);
    },

    getSiteStats(){
        return http.get('/');
    }
}