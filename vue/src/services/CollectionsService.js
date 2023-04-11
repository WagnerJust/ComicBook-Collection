import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:9000/api"
});

export default{

    listAllPublic(){
        return http.get('/collections'); 
    },

    listByUserId(userId){
        return http.get(`/${userId}/collections`);
    },
    
    createCollection(newCollection){
        return http.post(`/collections/`, newCollection);
    },
    
    updateCollection(updatedCollection){
        return http.put(`/collections.${updatedCollection.collectionId}`, updatedCollection);
    }
    
}