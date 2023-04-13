<template>
    <div class="container">
        <the-header />
        <h2 class="collections-title">My Collections</h2>
        <div class="test-collection-list">
            <div class="collectionBoxes" :collection="collection" v-for="collection in this.$store.state.myCollections" :key="collection.collectionId">
                <div class="collectionBox">
                    {{collection.collectionName}}
                    <router-link class="router-link" :to="{ name: 'my-collection' }" >LINK</router-link>
                </div>
            </div>
        </div>
        <h2 class="collections-title">Public Collections</h2>
        <div class="test-collection-list">
            <div class="collectionBoxes" :collection="collection" v-for="collection in this.$store.state.publicCollections" :key="collection.collectionId">
                <div class="collectionBox">
                    {{collection.collectionName}}
                    <router-link class="router-link" :to="{ name: 'my-collection' }"  >LINK</router-link>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import TheHeader from '../components/TheHeader.vue';
import collectionService from '../services/CollectionsService.js';

export default {
    name: 'collections',
    components: { 
        TheHeader,
    },
    created() {

        collectionService.listByUserId(this.$store.state.user.id).then(response => {
            this.$store.state.myCollections = response.data;
            console.log("My Collections")
            console.log(response.data)            
        })
        
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

        
        // /* This call can be seen in the console. It is requesting the comics from collectionId: 1 */
        // comicService.getComicsByCollectionId(1).then(response => {
        //     this.comics = response.data;
        //     console.log("COMICS")
        //     console.log(response.data)
        // })

        /* This call can be seen in the console. It is requesting all the public collections */
        collectionService.listAllPublic().then(response => {
            this.$store.state.publicCollections = response.data;
            console.log("Collections")
            console.log(response.data)
        })

    },
    methods: {
        setCollectionId() {
            this.$store.state.collectionId = this.collection.collectionId;
        },

        viewCollection(id) {
            this.$router.push(`/collection/${id}`)
        }
    }
}
</script>


<style scoped>

.collections-title {
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