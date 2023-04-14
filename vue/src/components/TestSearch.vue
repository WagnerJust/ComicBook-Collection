components / SearchComics

<template>
  <div class="search-comics">

    <div class="search-container">
        <input class="search-box" type="text" placeholder="  Search" v-model="searchValue" />
    </div>
    <div class="options-bar">
        <label>
            <input type="radio" name="search-option" value="comidId" v-model="selectedOption"> comidId
        </label>
        <label>
            <input type="radio" name="search-option" value="title" v-model="selectedOption"> Title
        </label>
        <label>
            <input type="radio" name="search-option" value="issueNumber" v-model="selectedOption"> issueNumber
        </label>
        <label>
            <input type="radio" name="search-option" value="writer" v-model="selectedOption"> writer
        </label>
        <label>
            <input type="radio" name="search-option" value="penciler" v-model="selectedOption"> penciler
        </label>
    </div>
        <div class="new-comics-list">
            <comic-card v-bind:comic="comic" v-for="comic in filteredComics" v-bind:key="comic.comicId"/>
        </div>

  </div>
</template>

<script>
import ComicCard from './ComicCard.vue';


export default {
    name: "test-search-comics",
    components: { ComicCard },
    data() {
        return {
            publicCollections: [],
            searchResults: [],
            searchValue: "",
            selectedOption: "title",
        }
    },
    computed: {
        comics() {
            return this.$store.state.comics
        },

        filteredComics() {
            const searchValue = this.searchValue.toLowerCase();
            const selectedOption = this.selectedOption;
            return this.comics.filter((comic) => {
                const value = comic[selectedOption].toLowerCase();
                return value.includes(searchValue);
            });
        }
    }
    
}

</script>

<style scoped>

/* .search-comics {
    display: ;
} */



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

.options-bar {
    position: absolute;
    left: 40%;
    top: 75%;
}


/* Test data */
.new-comics-list {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    position: absolute;
    flex-wrap: wrap;
    top: 80%;
    left: 15%;

}

.comic-card {
    width: 200px;
    height: 300px;
}

</style>