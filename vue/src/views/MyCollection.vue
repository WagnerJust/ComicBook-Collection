<template>
    <section class="container">
        <h2>{{collection.collectionName}}</h2>
        <div class="cards">
            <max-comic-card v-for="comic in this.comics" :key="comic.comicId" :comic="comic" />
        </div>
    </section>
</template>


<script>
import MaxComicCard from '../components/MaxComicCard.vue';
import comicService from '../services/ComicService.js'
import collectionService from "../services/CollectionsService.js"

export default {
    name: 'collection',
    components: { 
        MaxComicCard
    },
    data() {
        return {
            comics: [],
            collection: []
        }
    },
    created() {
        /* This call can be seen in the console. It is requesting the comics from collectionId: 1 */
        comicService.getComicsByCollectionId(this.$route.params.collectionId).then(response => {
            this.comics = response.data;
            console.log("COMICS");
            console.log(this.comics);
        });
        collectionService.getCollectionByCollectionId(this.$route.params.collectionId).then(response => {
            this.collection = response.data;
        });
        
    }
}
</script>


<style scoped>

.container {
    margin-left: 12%;
    margin-right: 12%;
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

</style>