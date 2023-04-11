import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default{

    listAllPublic(){
        return http.get('/collections'); 
    },

    listByUserId(userId){
        return http.get(`/${userId}/collections`);
    }

    
    
}