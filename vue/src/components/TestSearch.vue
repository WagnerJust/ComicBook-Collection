components / SearchComics

<template>
    <div class="search-comics">

        <div class="empty"></div>

        <div class="search-container">
            <input class="search-box" type="search" placeholder="Search" v-model="searchValue" />
        </div>

        <div class="options-bar">
            <label class="radio-box">
                <input type="radio" name="search-option" value="comidId" v-model="selectedOption" />
                <span class="name">UPC</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="title" v-model="selectedOption">
                <span class="name">Title</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="issueNumber" v-model="selectedOption">
                <span class="name">Issue Number</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="writer" v-model="selectedOption">
                <span class="name">Writer</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="penciler" v-model="selectedOption">
                <span class="name">Artist</span>
            </label>
        </div>

        <div class="new-comics-list">
            <comic-card v-bind:comic="comic" v-for="comic in filteredComics" v-bind:key="comic.comicId"/>
        </div>

    </div>
</template>

<script>
import ComicCard from './ComicCard.vue';
import comicService from '../services/ComicService.js';


export default {
    name: "test-search-comics",
    components: { ComicCard },
    data() {
        return {
            publicCollections: [],
            searchResults: [],
            testComics: [],
            searchValue: "",
            selectedOption: "title",
        }
    },
	created() {
		comicService.getComicsByCollectionId(8).then(response => {
		this.testComics = response.data;
		console.log("COMICS");
		console.log(this.comics);
        });
	},
    computed: {
        comics() {
            return this.testComics
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



.search-comics {
    display: grid;
    grid-template-rows: 410px 1fr 1.5fr auto;
    grid-template-areas:
        "empty"
        "search-box"
        "options-bar"
        "comics-list";
    justify-items: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.empty {
    grid-area: empty;
}

.search-container {
    grid-area: search-box;
    content: image-set("/public/search-icon.png" 2px);
}

.options-bar {
    grid-area: options-bar;
    align-self: center;
    display: flex;
    flex-wrap: wrap;
    border-radius: 0.5rem;
    background-color: #e1edff;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    padding: 0.25rem;
    width: 600px;
    font-size: 20px;
    margin-bottom: 20px;
}

.options-bar .radio-box {
    flex: 1 1 auto;
    text-align: center;
}

.options-bar .radio-box input {
    display: none;
}

.options-bar .radio-box .name {
    display: flex;
    cursor: pointer;
    align-items: center;
    justify-content: center;
    border-radius: 0.5rem;
    border: none;
    padding: 0.5rem 0;
    color: rgba(51, 65, 85, 1);
    font-weight: 600;
    transition: all .15s ease-in-out;
}

.options-bar .radio-box input:checked + .name {
    background-color: #ed1d24;
    font-weight: 600;
    color: white;
}

.search-box {
    grid-area: search-box;
    width: 50rem;
    height: 5rem;
    border-radius: 50px;
    border: 1px solid #ccc;
    padding: 10px 20px 10px 60px;
    font-size: 20px;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    background-image: url("/public/search-icon.png");
    background-repeat: no-repeat;
    background-size: 1.1em 1.1em;
    background-position: 3% 48%;;
}




/* Test data */
.new-comics-list {
    grid-area: comics-list;
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 10px;

}

.comic-card {
    width: 100%;
    height: 300px;
}

</style>