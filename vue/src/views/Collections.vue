<template>
    <div class="container">
        <the-header />
        <h2 class="collections-title">My Collections</h2>
        <div class="test-collection-list">
            <div class="collectionBoxes" v-for="collection in this.myCollections" :key="collection.collectionId">
                <div class="collectionBox">
                    {{collection.collectionName}}
                    <router-link class="router-link" :to="{ name: 'my-collection' }" >LINK</router-link>
                </div>
            </div>
        </div>
        <h2 class="collections-title">Public Collections</h2>
        <div class="test-collection-list">
            <div class="collectionBoxes" v-for="collection in this.publicCollections" :key="collection.collectionId">
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
    data() {
        return {
            myCollections: [],
            publicCollections: []
        }
    },
    created() {

        /* This call can be seen in the console. It is requesting all the current user's collections */
        collectionService.listByUserId(this.$store.state.user.id).then(response => {
            this.myCollections = response.data;
            console.log("My Collections");
            console.log(this.myCollections);         
        })

        /* This call can be seen in the console. It is requesting all the public collections */
        collectionService.listAllPublic().then(response => {
            this.publicCollections = response.data;
            console.log("Public Collections");
            console.log(this.publicCollections);
        })

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