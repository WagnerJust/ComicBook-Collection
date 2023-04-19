<template>
    <div class='stats-tables'>
         <div class="options-bar">
            <label class="radio-box">
                <input type="radio" name="search-option" value="characters" v-model="selectedOption" v-on:click="characterStats()"/>
                <span class="name">Characters</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="author" v-model="selectedOption" v-on:click="authorStats()"/>
                <span class="name">Authors</span>
            </label>
            <label class="radio-box">
                <input type="radio" name="search-option" value="series" v-model="selectedOption" v-on:click="seriesStats()"/>
                <span class="name">Series</span>
            </label>
        </div>
        <div class='character-stats' v-if="selectedOption === 'characters'">
            <table class='stats'>
                <thead>
                    <tr>
                        <th>Character</th>
                        <th># Comics ft. character</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="character in collectionStats" v-bind:key="character.name">
                        <td>{{character.name}}</td>
                        <td>{{character.num_comics_character}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='author-stats' v-if="selectedOption === 'author'">
            <table class='stats'>
                <thead>
                    <tr>
                        <th>Author</th>
                        <th># Comics by Author</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="character in collectionStats" v-bind:key="character.author">
                        <td>{{character.author}}</td>
                        <td>{{character.num_comics_author}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='series-stats' v-if="selectedOption === 'series'">
            <table class='stats'>
                <thead>
                    <tr>
                        <th>Series</th>
                        <th># Comics by Series</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="series in collectionStats" v-bind:key="series.series">
                        <td>{{series.series}}</td>
                        <td>{{series.num_comics_series}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
  </div>

</template>

<script>

import statsService from '../services/StatsService';


export default {
    name: 'stats-collection-table',
    data(){
        return{
            collectionStats: [],
            selectedOption: 'characters'

        }
    },
    methods:{
        characterStats(){
            statsService.getCharactersStats(this.$route.params.id)
        .then(response => {
            this.collectionStats = response.data;
            console.log('character stats');
            console.log(this.collectionStats);
        });},

        authorStats(){
            statsService.getAuthorStats(this.$route.params.id)
        .then(response => {
            this.collectionStats = response.data;
            console.log('author stats');
            console.log(this.collectionStats);
        });},

        seriesStats(){
            statsService.getSeriesStats(this.$route.params.id)
        .then(response => {
            this.collectionStats = response.data;
            console.log('series stats');
            console.log(this.collectionStats);
        });}
    },

    created(){
        statsService.getCharactersStats(this.$route.params.id)
        .then(response => {
            this.collectionStats = response.data;
        })
        console.log('stats');
        console.log(this.collectionStats);
    }

}
</script>

<style scoped>
.stats-tables {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 2rem;
}

table.stats {
  font-family: "Comic Sans MS", cursive, sans-serif;
  border: 1px solid#ed1d24;
  background-color: #EEEEEE;
  display: inline-flexbox;
  align-content: center;
  justify-content: center;
  
  text-align: left;
  border-collapse: collapse;
}
table.stats td, table.stats th {
  border: 1px solid #AAAAAA;
  padding: 5px 10px;
}
table.stats tbody td {
  font-size: 20px;
  color: #333333;
}
table.stats tr:nth-child(even) {
  background: #F5F5F5;
}
table.stats thead {
  background: #ed1d24;
  border-bottom: 2px solid #444444;
}
table.stats thead th {
  font-size: 20px;
  font-weight: bold;
  color: #FFFFFF;
  border-left: 3px solid #D0E4F5;
}
table.stats thead th:first-child {
  border-left: none;
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
.stats-tables{
    display: flex;
    margin-left: 12%;
    margin-right: 12%;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    margin-top: 3rem;
}

</style>