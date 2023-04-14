components / SearchComics

<template>
  <div class="search-comics">
    <img class="image-container" src="../../public/star-wars.jpg" alt="star-wars image">
    <div class="search-container">
        <input class="search-box" type="text" placeholder="  Search" v-model="searchValue" />
    </div>
    <div class="options-bar">
        <label>
            <input type="radio" name="search-option" value="character" v-model="selectedOption"> Character
        </label>
        <label>
            <input type="radio" name="search-option" value="title" v-model="selectedOption"> Title
        </label>
        <label>
            <input type="radio" name="search-option" value="series" v-model="selectedOption"> Series
        </label>
        <label>
            <input type="radio" name="search-option" value="creator" v-model="selectedOption"> Creator
        </label>
        <label>
            <input type="radio" name="search-option" value="issue" v-model="selectedOption"> Issue
        </label>
    </div>

    <!-- <div class="filtered-comics-list">
        <comic-card v-bind:comic="comic" />
        <ul>
            <li v-for="comic in comics" :key="comic.id">{{ comic.name }}</li>
        </ul>
    </div> -->

  </div>
</template>

<script>
// import collectionsService from '../services/CollectionsService';
import marvelService from '../services/MarvelService';
// import ComicCard from './ComicCard.vue';


export default {
    name: "search-comics",
    // components: { ComicCard },
    data() {
        return {
            publicCollections: [],
            searchResults: [],
            searchValue: "",
            selectedOption: "character",
            comics: []
        }
    },

        created() {
            
        marvelService.SearchComicByCharacter(1009610).then(response => {
            this.comics = response.data;
        })
        .catch(error => {
            console.error(error);
        });
    },
    
    methods: {
    searchComicByTitle(title) {
        marvelService.searchComicByTitle(title).then((response) => {
        this.searchResults = response.data;
        })
    },

    searchComicBySeries(series) {
        marvelService.searchComicBySeries(series).then((response) => {
        this.searchResults = response.data;
        })   
    },
    
    searchComicByCreator(name) {
        marvelService.searchComicByCreator(name).then((response) => {
        this.searchResults = response.data;
        })   
    },

    searchComicByIssue(title, issue) {
        marvelService.searchComicByIssue(title, issue).then((response) => {
        this.searchResults = response.data;
        })   
    },

    SearchComicByCharacter(characterId) {
        marvelService.SearchComicByCharacter(characterId).then((response) => {
        this.searchResults = response.data;
        })   
    },

    watch: {
        
    }
    


    // created() {
    //     collectionsService.listAllPublic().then((response) => {
    //         this.publicCollections = response.data;
    //         console.log(this.publicCollections)
    //     })
    // }

}
};

</script>

<style scoped>

/* .search-comics {
    display: ;
} */

.image-container {
    width: 100%;
    height: 30em;
    object-fit: cover;
    object-position: 100% 70%;
    position: absolute;
}

.search-container {
    position: absolute;
    left: 29%;
    top: 63%;
}

.search-box {
    width: 50rem;
    height: 5rem;
    border-radius: 50px;

}
</style>