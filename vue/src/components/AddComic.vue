<template>
    <div class="container">
        <div class="collection-box" v-if="showCollections === true">
            <h5>Which collection?</h5>
            <ul>
                <a v-for="collection in this.myCollections" v-bind:key="collection.collectionId"><li>{{collection.collectionName}}</li></a>
            </ul>
            <button id="cancel-button" v-on:click.prevent="showCollections = false">Cancel</button>
        </div>
        <button v-if="showCollections === false" v-on:click.prevent="showCollections = true" v-bind:style="{}">Add To Collection</button>
    </div>
</template>


<script>
import collectionService from '../services/CollectionsService.js';

export default {
    data() {
        return {
            showCollections: false,
            myCollections: []
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

.container {
    display: flex;
    justify-content: flex-end;
    margin-top: 1rem;
    margin-bottom: 1rem;
}

.collection-box {
    background-color: white;
    border: 1px solid black;
    width: 100%;
    padding: 5px;
}

ul {
    display: flex;
    flex-direction: column;
    text-align: left;
    list-style-type: none;
}

a {
    text-decoration: underline;
}

a:hover {
    font-weight: 500;
    letter-spacing: 0;
}

ul::before {
    font-weight: 500;
}

li {
    cursor: pointer;
    margin: .2rem;
}

button {
  cursor: pointer;
  border-radius: 4px;
  font-weight: 600;
  margin-top: .5rem;
  width: 5rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
  transition: 0.4s;
  color: black;
  background-color: white;
  border: 1px solid black;
  font-family: 'Montserrat', Helvetica, sans-serif;
}

button:hover {
  color: white;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  background-color: black;
}

h5 {
    margin: .2rem;
}

#cancel-button {
    width: 4.2rem;
    margin-bottom: .5rem;
}

</style>