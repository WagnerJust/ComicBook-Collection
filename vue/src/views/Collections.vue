<template>
    <div>
        <div id="collection-banner"></div>
        <section class="container">

            <h2 class="collections-title">My Collections</h2>
            <add-collection />
            <div class="collection-list">
                <router-link class="collectionBox" 
                :to="{ name: 'collection', params: {id: collection.collectionId} }" 
                v-for="collection in this.myCollections" 
                :key="collection.collectionId">
                <div class="collection-card">
                    <div class="collection-name" >{{collection.collectionName}}</div>
                    <img class="collection-image" v-bind:src="getImage(collection.collectionId)" />
                </div>
                </router-link>
            </div>
            <h2 class="collections-title">Public Collections</h2>
            <div class="collection-list">
                <router-link class="collectionBox" 
                :to="{ name: 'public-collection', params: {id: collection.collectionId} }" 
                v-for="collection in this.publicCollections" 
                :key="collection.collectionId">
                <div class="collection-card">
                    <div class="collection-name">{{collection.collectionName}}</div>
                    <img class="collection-image" v-bind:src="getImage(collection.collectionId)" />
                </div>
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
            publicCollections: [],
            images: require.context('/public/collections_img', false, /\.png$/),
            imageCache: {}
        }
    },

    methods: {
        getRandomImages() {
            console.log("IMAGES")
            console.log(this.images.keys())
            const imgs = this.images.keys().map(key => this.images(key))
            const randomIndex = Math.floor(Math.random() * imgs.length)
            return imgs[randomIndex]
        },

        getImage(collectionId) {
            if (localStorage.getItem(collectionId)) {
                return localStorage.getItem(collectionId);
            } else {
                const image = this.getRandomImages()
                localStorage.setItem(collectionId, image);
                return image
            }
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
    border-radius: 10%;
    text-decoration: none;
}

.collectionBox:hover {
    color: #ED1D24;
}

.collection-name {
    margin-top: .3rem;
    margin-bottom: .1rem;
}

.collection-card {
    height: 300px;
    width: 240px;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    display: grid;
    grid-template-areas:
        "name"
        "image";
    grid-template-rows: 1fr auto;
    transition: .3s ease;
    border-radius: 10%;
    color: black;
    border-bottom-left-radius: 0%;
}

.collection-name {
    grid-area: name;
    align-self: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
    font-weight: 800;
    font-size: 1.2rem;
}

.collection-name:hover {
    color: #ED1D24;
}

.collection-image {
    grid-area: image;
    height: 240px;
    bottom: 0%;
    border-bottom-right-radius: 10%;
}

.collection-card:hover {
    box-shadow: 0 0 0 7px #ed1d24;
    transition: .3s ease;
    color: #ED1D24;
}


</style>