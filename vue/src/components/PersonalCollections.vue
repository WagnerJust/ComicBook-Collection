<template>
    <div class="main">
            <h2 class="collections-title">My Collections</h2>
            <add-collection />
            <div class="collection-list">
                <router-link class="collection-box" 
                :to="{ name: 'collection', params: {id: collection.collectionId} }" 
                v-for="collection in this.myCollections" 
                :key="collection.collectionId">
                <div class="collection-card">
                    <div class="collection-name" >{{collection.collectionName}}</div>
                    <img class="collection-image" v-bind:src="getImage(collection.collectionId)" />
                </div>
                </router-link>
            </div>
    </div>
</template>

<script>
import AddCollection from '../components/AddCollection.vue';
import collectionService from '../services/CollectionsService.js';

export default {
    name: 'personal-collection',
    data() {
        return {
            myCollections: [],
            publicCollections: [],
            images: require.context('/public/collections_img', false, /\.png$/),
            imageCache: {}
        }
    },
    components: {
        AddCollection,
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
    created() {

        collectionService.listByUserId(this.$store.state.user.id).then(response => {
            this.myCollections = response.data;
            console.log("My Collections");
            console.log(this.myCollections);         
        })

    }
}
</script>

<style scoped>

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

.collection-box {
    text-transform: uppercase;
    text-align: center;
    background-color: white;
    margin: 1.5rem;
    border-radius: 10%;
    text-decoration: none;
}

.collection-box:hover {
    color: #ED1D24;
}

.collection-name {
    margin-top: .3rem;
    margin-bottom: .1rem;
}

.collection-card {

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