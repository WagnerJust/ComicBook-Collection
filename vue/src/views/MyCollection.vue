<template>
    <section class="main">
        <div id="collection-banner"></div>
        <div class="container">
            <h2>{{collection.collectionName}}</h2>
            <edit-collection />
            <div class="cards">
                <comic-card v-for="comic in this.comics" :key="comic.comicId" :comic="comic" />
            </div>
            <h2>Statistics</h2>
            <stats-collection-table/>
            <delete-collection v-if="this.$route.name === 'collection'" />
        </div>
    </section>
</template>


<script>
import EditCollection from '../components/EditCollection.vue';
import ComicCard from '../components/ComicCard.vue';
import DeleteCollection from '../components/DeleteCollection.vue';
import comicService from '../services/ComicService.js';
import collectionService from "../services/CollectionsService.js";
import StatsCollectionTable from '../components/StatsCollectionTable.vue';

export default {
    name: 'collection',
    components: { 
        EditCollection,
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
        });

    }
}
</script>


<style scoped>

#collection-banner {
    background-image: url('/public/marvel-panorama-2.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0% 70%;
	height: 450px;
}

.container {
    margin-left: 12%;
    margin-right: 12%;
}

.cards {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    margin-top: 1.5rem;
}

h2 {
    text-align: center;
    margin: 1.5rem;
    font-size: 2.5rem;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

</style>

