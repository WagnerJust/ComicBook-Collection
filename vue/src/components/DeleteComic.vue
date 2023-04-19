<template>
    <div class="container">
        <button id="delete-button" v-on:click="deleteComic()">Delete From Collection</button>
    </div>
</template>

<script>
import collectionService from '../services/CollectionsService.js';
import comicService from '../services/ComicService.js';

export default {
    props: {
        comic: Object,
    },
    methods: {
        deleteComic() {
            let longCollectionId = this.$route.path;
            console.log("Long Collection ID");
            console.log(longCollectionId);
            let collectionId = longCollectionId.substring(longCollectionId.lastIndexOf('/') + 1)
            console.log("Collection ID");
            console.log(collectionId);
            let comicId = this.comic.comicId;
            comicService.removeComicFromCollection(collectionId, comicId).then(response => {
                if (response.status === 200) {
                    console.log("Delete Status")
                    console.log(response.status);
                    this.$router.go("/collection/" + collectionId);
                }
            })
        }
    },
    created() {
        collectionService.listByUserId(this.$store.state.user.id).then(response => {
        this.myCollections = response.data;       
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

#delete-button {
    width: 13rem;
    height: 2.2rem;
    font-size: 1rem;
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