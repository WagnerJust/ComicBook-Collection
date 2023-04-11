import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    comics: [
      {
        comicId: "67631",
        title: "The Amazing Spider-Man (2018) #1",
        issueNumber: "1",
        creators: [
          {
            name: "Nick Spencer",
            role: "writer" 
          },
          {
            name: "Ryan Ottley",
            role: "penciler (cover)" 
          }
        ],
        image: "http://i.annihil.us/u/prod/marvel/i/mg/b/90/5b3feb60f1a30/"
      },
      {
        comicId: "44198",
        title: "Thor: God of Thunder (2012) #1",
        issueNumber: "1",
        creators: [
          {
            name: "Jason Aaron",
            role: "writer" 
          },
          {
            name: "Esad Ribic",
            role: "artist (cover)" 
          }
        ],
        image: "http://i.annihil.us/u/prod/marvel/i/mg/3/30/51646f6c2a712/"
      },
    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
