import axios from "axios";
import store from "../store/index.js"

const http = axios.create({
    baseURL: "http://localhost:9000/api",
    headers: {
        "Authorization": `Bearer ${store.state.token}`
    }    
});

export default{

    getCharacterById(characterId){
        return http.get(`/characters/${characterId}`);
    },

    getCharacterByAlias(characterAlias){
        return http.get(`/characters/alias/${characterAlias}}`);
    },

    getCharactersByComicId(comicId){
        return http.get(`/comics/${comicId}/characters`);
    },

    getAllCharacters(){
        return http.get('/characters');
    },

    addCharacter(characterToAdd){
        return http.post(`/characters/add`,characterToAdd);
    },

    addCharacterToComic(comicId, characterId){
        return http.post(`/comics/${comicId}/characters/${characterId}`);
    },

    updateCharacter(updatedCharacter){
        return http.put(`/characters/${updatedCharacter.characterId}`, updatedCharacter);
    },

    deleteCharacter(characterId){
        return http.delete(`/characters/delete/${characterId}`);
    }
}


