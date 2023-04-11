<template>
    <section>
        <h2 class="new-comics-text">New Comics</h2>
        <div class="new-comics-list" >
            <comic-card />
        </div>       
    </section>
</template>

<script>
import ComicCard from './ComicCard.vue'
import collectionService from '../services/CollectionsService.js';

export default {
    name: "new-comics-list",
    computed: {
        comics() {
            return this.$store.state.testComics
        }
    },
    components: { 
        ComicCard 
    },
    methods: {
        retrieveCollectionById() {
            collectionService.listByUserId(3).then(response => {
                this.$store.commit("SET_COLLECTIONS", response.data)
            })
        }
    }    
}
</script>

<style>

.new-comics-text {
    margin-top: 4%;
}

.new-comics-list {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    margin-top: 2%;
}
</style>