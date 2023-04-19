components / SearchComics

<template>
    <div class="search-comics">

        <div class="empty"></div>

        <div class="search-container">
            <input class="search-box" v-show="!marvelApi" type="search" placeholder="Search" v-on:keyup.enter="searchDb" v-model="searchValueDb" />
        </div>
        <div class="search-container">
            <input class="search-box" v-show="searchBarApiUpc" type="search" placeholder="UPC" v-on:keyup.enter="searchUpc" v-model="searchValue" />
        </div>
        <div class="search-container">
            <input class="search-box" v-if="searchBarApiIssueNo" type="search" placeholder="Series" v-on:keyup.enter="searchSeriesIssue" v-model="searchValueSeries" />
        </div>
        <div class="search-containerTwo">
            <input class="search-boxTwo" v-if="searchBarApiIssueNo" type="search" placeholder="Issue Number" v-on:keyup.enter="searchSeriesIssue" v-model="searchValueIssue" />
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
            searchValueDb: "",
            searchValueSeries: "",
            searchValueIssue: "",
            selectedOption: "seriesName",
            selectedOptionApi: "upc",
            marvelApi:true,
            
            
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

        searchBarApiUpc(){
            if(this.marvelApi == true && this.selectedOptionApi == "upc"){
            return true;
            }
            return false;
        },

        searchBarApiIssueNo(){
            if(this.marvelApi == true && this.selectedOptionApi == "seriesAndIssue"){
            return true;
            }
            return false;
        }

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
            console.log(response.data[0]);
            })
        },

        searchSeriesIssue() {
            console.log("SEARCH SERIES CALLED");
            this.searchValueSeries 
            marvelService.searchComicBySeriesAndIssue(this.searchValueSeries, this.searchValueIssue).then(response =>{
                this.searchResults = response.data;
                response.data.forEach(element => {
                    console.log("ADDCOMIC CALLED WITH:")
                    console.log(element)
                    comicService.addComic(element);
                    
                });
                console.log("RESPONSE DATA");
            console.log(response.data);
            })

        }
        
    }
}

</script>

<style scoped>




.search-comics {
    display: grid;
    grid-template-rows: 345px 1fr 1fr 2fr auto;
    grid-template-columns: 1fr 268px 160px 376px 1fr;
    grid-template-areas:
        ". . . . ."
        ". options-bar options-bar marvel-toggle ."
        ". search-box search-box search-box ."
        ". search-boxTwo search-boxTwo . ."
        "comics-list comics-list comics-list comics-list comics-list";

    font-family: 'Montserrat', Helvetica, sans-serif;
}

.empty {
    grid-area: empty;
}

.search-container {
    grid-area: search-box;
    content: image-set("/public/search-icon.png" 2px);
    justify-self: center;
}

.search-containerTwo {
    grid-area: search-boxTwo;
    content: image-set("/public/search-icon.png" 2px);
}

.options-bar {
    grid-area: options-bar;
    align-self: flex-start;
    display: flex;
    flex-wrap: wrap;
    border-radius: 0.5rem;
    background-color: #e1edff;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    padding: 0.25rem;
    width: 400px;
    font-size: 20px;
    margin-bottom: 20px;
    justify-self: start;
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
    height: 4rem;
    border-radius: 0.5rem;
    border: 1px solid #ccc;
    padding: 10px 20px 10px 60px;
    font-size: 20px;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    background-image: url("/public/search-icon.png");
    background-repeat: no-repeat;
    background-size: 1.1em 1.1em;
    background-position: 3% 48%;;
}

.search-boxTwo {
    grid-area: search-boxTwo;
    width: 50rem;
    height: 4rem;
    border-radius: 0.5rem;
    border: 1px solid #ccc;
    padding: 10px 20px 10px 60px;
    font-size: 20px;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    background-image: url("/public/search-icon.png");
    background-repeat: no-repeat;
    background-size: 1.1em 1.1em;
    background-position: 3% 48%;;
}

#marvel-toggle {
    grid-area: marvel-toggle;
    background-color: rgba(0, 0, 0, 0.7);
    padding: 5px 5px 5px 5px;
    border-radius: 0.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
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
    height: 30px;
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
    background-color: #232ece;
    -webkit-transition: .4s;
    transition: .4s;
}

#slider:before {
    position: absolute;
    content: "";
    height: 22px;
    width: 24px;
    left: 4px;
    bottom: 4px;
    background-color: white;
    -webkit-transition: .4s;
    transition: .4s;
}

input:checked + #slider {
    background-color: #ED1D24;
    box-shadow: 0 0 1px #ED1D24;
}

input:checked + #slider:before {
    -webkit-transform: translateX(26px);
    -ms-transform: translateX(26px);
    transform: translateX(26px);
}

#marvel-toggle #toggle-text {
    color: white;
    font-weight: 600;
    text-transform: uppercase;
    text-align: center;
}

</style>


