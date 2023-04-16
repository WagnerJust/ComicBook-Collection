import axios from "axios";
import store from "../store/index.js"

const http = axios.create({
    baseURL: "http://localhost:9000/api",
    headers: {
        "Authorization": `Bearer ${store.state.token}`
    }    
});

export default{


    getCollectionByCollectionId(collectionId){
        return http.get(`/collections/${collectionId}`);
    },

    listAllPublic(){
        return http.get('/collections'); 
    },

    listByUserId(userId){
        return http.get(`user/${userId}/collections`);
    },
    
    createCollection(newCollection){
        return http.post(`/collections/add`, newCollection);
    },
    
    updateCollection(updatedCollection){
        return http.put(`/collections/update/${updatedCollection.collectionId}`, updatedCollection);
    },

    numberComicsInCollectionWithCharacter(collectionId, characterId){
        return http.get(`/collections/${collectionId}/stats/${characterId}`);
    },

    numberComicsWithCharacterTotal(userId, characterId){
        return http.get(`/user/${userId}/characters/${characterId}`);
    }
    
}