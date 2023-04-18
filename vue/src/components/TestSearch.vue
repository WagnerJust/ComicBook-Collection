components / SearchComics

<template>
    <div class="search-comics">

        <div class="empty"></div>

        <div class="search-container">
            <input class="search-box" type="search" placeholder="Search" v-on:keyup.enter="searchUpc" v-model="searchValue" />
        </div>

        <div class="options-bar" v-show="!marvelApi">
            <label class="radio-box">
                <input type="radio" name="search-option" value="upc" v-model="selectedOption" />
                <span class="name">UPC</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="seriesName" v-model="selectedOption">
                <span class="name">Series</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="issueNumber" v-model="selectedOption">
                <span class="name">Issue Number</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="author" v-model="selectedOption">
                <span class="name">Writer</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="artist" v-model="selectedOption">
                <span class="name">Artist</span>
            </label>
        </div>
         <div class="options-bar" v-show="marvelApi">
              <label class="radio-box">
                <input type="radio" name="search-option" value="upc" v-model="selectedOptionApi" />
                <span class="name">UPC</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="seriesAndIssue" v-model="selectedOptionApi">
                <span class="name">Series and Issue Number</span>
            </label>
         </div>
        <div id="marvel-toggle" class="field">
                <label id="toggle-text" for="checkbox">Search This Site</label>
                <label id="switch"><input type="checkbox" v-model="marvelApi" /><span id="slider"></span></label>
                <label id="toggle-text" for="checkbox">Search Marvel API</label>
        </div>

        <div class="new-comics-list">
            <comic-card-marvel-api :comic="comic" v-for="comic in searchResults" :key="comic.upc"/>
        </div>
        

    </div>
</template>

<script>
import ComicCardMarvelApi from './ComicCardMarvelApi.vue';
import marvelService from '../services/MarvelService.js';
import comicService from '../services/ComicService.js';

export default {
    name: "test-search-comics",
    components: { ComicCardMarvelApi },
    data() {
        return {
            searchResults: [ 

            ],
            searchValue: "",
            selectedOption: "seriesName",
            selectedOptionApi: "upc",
            marvelApi:true
            
        }
    },
	created() {
        console.log("TEST");
		console.log(this.$store.state.comics);
        console.log("NEXT");
        console.log(this.comics)
	},
    computed: {
        comics() {
            return this.searchResults
        },

        // filteredComics() {
        //     const searchValue = this.searchValue.toLowerCase();
        //     const selectedOptionFilter = this.selectedOption;
        //     return this.comics.filter((comic) => {
        //         const value = comic[selectedOptionFilter].toLowerCase();
        //         return value.includes(searchValue);
        //     });
        // }
    },
    methods: {

        searchUpc(searchValue) {
            console.log("SEARCH IS CALLED");
            searchValue = this.searchValue;
            marvelService.searchComicByUpc(searchValue).then(response => {
            this.searchResults = response.data;
            comicService.addComic(response.data[0]);
        
            console.log("RESPONSE DATA");
            console.log(response.data);
            })
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
        "marvel-toggle"
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
    height: 100%;
    width: 100%;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 10px;

}

/* The toggle switch - this code was copied and slightly edited from: https://www.w3schools.com/howto/howto_css_switch.asp */

#switch {
    position: relative;
    display: inline-block;
    width: 60px;
    height: 34px;
}

#switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

#slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    -webkit-transition: .4s;
    transition: .4s;
}

#slider:before {
    position: absolute;
    content: "";
    height: 26px;
    width: 26px;
    left: 4px;
    bottom: 4px;
    background-color: white;
    -webkit-transition: .4s;
    transition: .4s;
}

input:checked + #slider {
    background-color: #23ce2b;
    box-shadow: 0 0 1px #23ce2b;
}

input:checked + #slider:before {
    -webkit-transform: translateX(26px);
    -ms-transform: translateX(26px);
    transform: translateX(26px);
}

</style>


