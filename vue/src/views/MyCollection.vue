<template>
    <div class="container">
        <the-header />
        my collection
        <max-comic-card v-for="comic in this.$store.state.testComics" :key="comic.comicId"/>
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
    created() {

        // collectionService.listAllPublic().then(response => {
        //     this.collections = response.data;
        //     console.log("COLLECTION DATA")
        //     console.log(this.collections)
        //     this.collections.forEach(collection => {
        //         comicService.getComicsByCollectionId(collection.collectionId).then(response => {
        //             this.comics += response.data;
        //         })
        //     })
        // }).then(console.log(this.comics))

        
        /* This call can be seen in the console. It is requesting the comics from collectionId: 1 */
        comicService.getComicsByCollectionId(this.myCollections).then(response => {
            this.$store.state.testComics = response.data;
        })

        // /* This call can be seen in the console. It is requesting all the public collections */
        // collectionService.listAllPublic().then(response => {
        //     this.collections = response.data;
        //     console.log("COLLECTIONS")
        //     console.log(response.data)
        // })

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