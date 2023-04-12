<template>
    <div class="container">
        <the-header />
        <!-- <div class="main">
            <new-comics-list />
        </div> -->
        <div class="test-comic" :comic="comic" v-for="comic in comics" :key="comic.comicId">
            <div>{{comic.comicId}}</div>
            <div>{{comic.imageURL}}</div>
            <div>{{comic.issueNumber}}</div>
            <div>{{comic.publish_date}}</div>
            <div>{{comic.upc}}</div>
        </div>
    </div>
</template>


<script>
import TheHeader from '../components/TheHeader.vue';
// import NewComicsList from '../components/NewComicsList.vue';
import collectionService from '../services/CollectionsService.js';
import comicService from '../services/ComicService.js';

export default {
    name: 'my-collections',
    components: { 
        TheHeader,
        // NewComicsList
    },
    data() {
        return {
            collections: [],
            comics: []
        };
    },
    created() {

        // collectionService.listAllPublic().then(response => {
        //     this.collections = response.data;
        //     console.log("COLLECTION DATA")
        //     console.log(this.collections)
        //     this.collections.forEach(collection => {
        //         comicService.geComicsByCollectionId(collection.collectionId).then(response => {
        //             this.comics.push(response.data);
        //         })
        //     })
        // }).then(console.log(this.comics))

        /* This call can be seen in the console. It is requesting the comics from collectionId: 1 */
        comicService.getComicsByCollectionId(1).then(response => {
            this.comics = response.data;
            console.log("COMICS")
            console.log(response.data)
        })

        /* This call can be seen in the console. It is requesting all the public collections */
        collectionService.listAllPublic().then(response => {
            this.collections = response.data;
            console.log("COLLECTIONS")
            console.log(response.data)
        })

    }
}
</script>


<style scoped>

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