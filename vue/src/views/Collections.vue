<template>
    <div>
        <div id="collection-banner">
        </div>
        <section class="container">

            <h2 class="collections-title">My Collections</h2>
            <add-collection />
            <div class="collection-list">
                <router-link class="collectionBox" 
                :to="{ name: 'collection', params: {id: collection.collectionId} }" 
                v-for="collection in this.myCollections" 
                :key="collection.collectionId">
                    <div class="collection-name">{{collection.collectionName}}</div>
                    <img class="collection-image" src="..\public\comic-collection.jpg" />
                </router-link>
            </div>
            <h2 class="collections-title">Public Collections</h2>
            <div class="collection-list">
                <router-link class="collectionBox" 
                :to="{ name: 'public-collection', params: {id: collection.collectionId} }" 
                v-for="collection in this.publicCollections" 
                :key="collection.collectionId">
                    <div class="collection-name">{{collection.collectionName}}</div>
                    <img class="collection-image" src="..\public\comic-collection.jpg" />
                </router-link>
            </div>
        </section>
    </div>
</template>


<script>
import collectionService from '../services/CollectionsService.js';
import AddCollection from '../components/AddCollection.vue';

export default {
    name: 'collections',

    data() {
        return {
            myCollections: [],
            publicCollections: []
        }
    },

    components: {
        AddCollection
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

.container {
    margin-left: 12%;
    margin-right: 12%;
}

#collection-banner {
    background-image: url('/public/marvel-panorama-3.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0% 70%;
	height: 450px;
}

#image {
    height: 500px;
    width: 200%;
}

.collections-title {
    margin-top: 1.5rem;
    font-size: 2.5rem;
    text-align: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.collection-list {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
}

.collectionBox {
    text-transform: uppercase;
    text-align: center;
    background-color: white;
    margin: 2rem;
}

.collectionBox:hover {
    color: #ED1D24;
}

.collection-name {
    margin-top: .3rem;
    margin-bottom: .1rem;
}

.collection-image:hover {
    transform: translateY(+5px);
}

</style>