<template>
    <section class="container">
        <div class="collection-info">
            <h2>{{collection.collectionName}}</h2>
            <p>Public?</p>
            <label id="switch"><input type="checkbox" v-model="collection.public" /><span id="slider"></span></label>
        </div>
        <div class="cards">
            <comic-card v-for="comic in this.comics" :key="comic.comicId" :comic="comic" />
        </div>
        <h2>Statistics</h2>
        <stats-collection-table/>
        <delete-collection v-if="this.$route.name === 'collection'" />
    </section>


    
</template>


<script>
import ComicCard from '../components/ComicCard.vue';
import DeleteCollection from '../components/DeleteCollection.vue';
import comicService from '../services/ComicService.js';
import collectionService from "../services/CollectionsService.js";
import StatsCollectionTable from '../components/StatsCollectionTable.vue';

export default {
    name: 'collection',
    components: { 
        ComicCard,
        DeleteCollection,
        StatsCollectionTable
    },
    data() {
        return {
            comics: [],
            collection: []

        }
    },
    methods:{
    

    },
    created() {

        comicService.getComicsByCollectionId(this.$route.params.id).then(response => {
            this.comics = response.data;
        });

        collectionService.getCollectionByCollectionId(this.$route.params.id).then(response => {
            this.collection = response.data;
            console.log("TEST")
            console.log(this.collection)
        });

    }
}
</script>


<style scoped>

.container {
    margin-left: 12%;
    margin-right: 12%;
}

.collection-info {
    display: flex;
    justify-content: center;
    align-items: center;
}

.collection-info p {
    margin-right: .5rem;
    font-size: 1.3rem;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.cards {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    margin-top: 3rem;
}

h2 {
    text-align: center;
    margin: 1.5rem;
    font-size: 2.5rem;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

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

