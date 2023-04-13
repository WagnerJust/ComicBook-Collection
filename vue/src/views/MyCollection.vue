<template>
    <div class="container">
        <the-header />
        my collection
        <max-comic-card v-for="comic in this.comics" :key="comic.comicId" :comic="comic" />
    </div>
</template>


<script>
import MaxComicCard from '../components/MaxComicCard.vue';
import TheHeader from '../components/TheHeader.vue';
import comicService from '../services/ComicService.js'

export default {
    name: 'collection',
    components: { 
        TheHeader,
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
            console.log("COMICS")
            console.log(this.comics)
        })

    }
}
</script>


<style scoped>

.my-collections-title {
    margin: 2rem;
    font-size: 3rem;
    text-align: center;
}

.test-collection-list {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    margin: 2rem;
}

.collectionBoxes {
    display: flex;
    flex-direction: row;
    height: 200px;
    width: 200px;
    justify-content: center;
    background-color: white;
    
}

.collectionBox {
    display: flex;
    justify-content: center;
    
}

.router-link {
    display: flex;
    align-items: flex-end;
    
}

.test-comic-list {
    display: flex;
    flex-direction: row;
}

.test-comic {
    display: flex;
    flex-direction: column;
    margin: 2rem
}

.main {
  margin-left: 12%;
  margin-right: 12%;
}

</style>