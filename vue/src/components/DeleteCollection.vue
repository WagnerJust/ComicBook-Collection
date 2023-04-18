<template>
    <div class="delete-container">
        <button id="delete-button" v-on:click="deleteCollection">DELETE COLLECTION</button>
    </div>
</template>


<script>
import collectionService from '../services/CollectionsService.js';

export default {
    props: {
        collection: Object
    },
    methods: {
        deleteCollection() {
            let collectionId = this.$route.params.id;
            let userId = this.$store.state.user.id;
            collectionService.deleteCollection(userId, collectionId).then(response => {
                if (response.status === 200) {
                    this.$router.push('/collections')
                }
            })
        }
    }
}
</script>

<style scoped>

.delete-container {
    display: flex;
    justify-content: flex-end;
    margin-top: 4rem;
    margin-bottom: 2rem;
}

#delete-button {
    cursor: pointer;
    color: black;
    background-color: red;
    height: 2rem;
    width: 10rem;
}

#delete-button:hover {
    color: white;
}

</style>