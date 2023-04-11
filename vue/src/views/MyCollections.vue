<template>
    <div class="main">
        <the-header />
        <new-comics-list />
    </div>
</template>


<script>
import TheHeader from '../components/TheHeader.vue';
import NewComicsList from '../components/NewComicsList.vue';
import collectionService from '../services/CollectionsService.js';
import comicService from '../services/ComicService.js';

export default {
    name: 'my-collections',
    components: { 
        TheHeader,
        NewComicsList,
    },
    data() {
        return {
            collections: [],
            comics: []
        };
    },
    created() {
        collectionService.listAllPublic().then(response => {
            this.collections = response.data;
            this.collections.forEach(collection => {
                comicService.geComicsByCollectionId(collection.collectionId).then(response => {
                    this.comics.push(response.data);
                })
            })
        }).then(console.log(this.comics))
    }
}
</script>


<style scoped>

.home {
  margin-left: 12%;
  margin-right: 12%;

}

</style>