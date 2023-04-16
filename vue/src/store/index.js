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
    comics: [],
    characters: [
      {
        characterId: "1009610",
        alias: "Spider-Man",
        name: "Peter Parker",
        image: "3/50/526548a343e4b"
      },
      {
        characterId: "1009165",
        alias: "Avengers",
        name: "",
        image: "9/20/5102c774ebae7"
      },
      {
        characterId: "1009718",
        alias: "Wolverine",
        name: "Logan",
        image: "2/60/537bcaef0f6cf"
      },
      {
        characterId: "1011299",
        alias: "Guardians of the Galaxy",
        name: "",
        image: "2/70/50febd8be6b5d"
      },
      {
        characterId: "1009268",
        alias: "Deadpool",
        name: "Wade Wilson",
        image: "9/90/5261a86cacb99"
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
