components / SearchComics

<template>
  <div class="search-comics">
    <img class="image-container" src="../../public/star-wars.jpg" alt="star-wars image">
    <div class="search-container">
        <input class="search-box" type="text" placeholder="  Search" v-model="searchValue" />
    </div>
    <div class="filtered-comics-list">
        <comic-card v-bind:comic="comic" />
    </div>

  </div>
</template>

<script>
import collectionsService from '../services/CollectionsService';
import marvelService from '../services/MarvelService';
import ComicCard from './ComicCard.vue';

export default {
    name: "search-comics",
    components: { ComicCard },
    data() {
        return {
            publicCollections: [],
            searchResults: [],
            searchValue: "",
        }
    },
    
    method: {
    searchByTitle(title) {
        marvelService.searchComicByTitle(title).then((response) => {
        this.searchResults = response.data;
        })
    },

    searchComicBySeries(series) {
        marvelService.searchComicByTitle(series).then((response) => {
        this.searchResults = response.data;
        })   
    },
    
    searchComicByCreator(name) {
        marvelService.searchComicByTitle(name).then((response) => {
        this.searchResults = response.data;
        })   
    },

    searchComicByIssue(title, issue) {
        marvelService.searchComicByTitle(title, issue).then((response) => {
        this.searchResults = response.data;
        })   
    },

    SearchComicByCharacter(characterId) {
        marvelService.searchComicByTitle(characterId).then((response) => {
        this.searchResults = response.data;
        })   
    },
    


    created() {
        collectionsService.listAllPublic().then((response) => {
            this.publicCollections = response.data;
            console.log(this.publicCollections)
        })
    }
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