<template>
    <div class="collection-box" v-if="showCollections === true">
        <h5>Which collection?</h5>
        <ul>
            <a v-for="collection in this.myCollections" v-bind:key="collection.collectionId" v-on:click="addComic" ><li>{{collection.collectionName}}</li></a>
        </ul>
        <button id="cancel-button" v-on:click.prevent="showCollections = false">Cancel</button>
    </div>
</template>

<style scoped>

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
    text-align: left;
}

#cancel-button {
    width: 4.2rem;
    margin-bottom: .5rem;
}

</style>