<template>
    <form class="collection-form" v-on:submit.prevent>
        <p class="field">
            - Add New Collection -
        </p>
        <div class="field">
            <input type="text" v-model="collection.collectionName" />
        </div>
        <div class="field">
            <label for="content">Public? </label>
            <input type="checkbox" v-model="collection.public" />
        </div>
        <div class="field">
            <button type="submit" v-on:click="saveCollection()">Save Collection</button>
        </div>
    </form>
</template>

<script>
import collectionService from '../services/CollectionsService.js';

export default {
    name: "add-collection",
    data() {
        return {
            collection: {
                userId: this.$store.state.user.id,
                collectionId: '',
                public: false
            }
        }
    },
    methods: {
        saveCollection() {
            collectionService.createCollection(this.collection).then(response => {
                if (response.status === 201) {
                    this.$router.go("/collections")
                }
            })
        }
    }
}
</script>

<style scoped>

    .collection-form {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 1rem;
        text-align: left;
        font-family: 'Montserrat', Helvetica, sans-serif;
    }

    .field {
        margin: .2rem;
    }

    button {
        cursor: pointer;
        border: 0;
        border-radius: 4px;
        font-weight: 600;
        margin: 1 10px;
        width: 8rem;
        padding: 10px 0;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
        transition: 0.4s;
        color: black;
        background-color: rgba(255, 255, 255, 1);
        border: 1px solid black;
        font-family: 'Montserrat', Helvetica, sans-serif;
    }
</style>