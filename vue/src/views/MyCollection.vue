<template>
    <section class="container">
        <h2>Collection Name</h2>
        <div class="cards">
            <max-comic-card v-for="comic in this.comics" :key="comic.comicId" :comic="comic" />
        </div>
    </section>
</template>


<script>
import MaxComicCard from '../components/MaxComicCard.vue';
import comicService from '../services/ComicService.js'

export default {
    name: 'collection',
    components: { 
        MaxComicCard
    },
    data() {
        return {
            comics: []
        }
    },
    created() {
        /* This call can be seen in the console. It is requesting the comics from collectionId: 1 */
        comicService.getComicsByCollectionId(this.$route.params.collectionId).then(response => {
            this.comics = response.data;
            console.log("COMICS");
            console.log(this.comics);
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