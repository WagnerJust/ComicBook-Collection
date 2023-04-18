<template>
    <section>
    <h2 class="new-comics-text">Latest Releases:</h2>
    <div class="new-comics-list" >
        <comic-card v-bind:comic="comic" v-for="comic in comics" v-bind:key="comic.comicId"/>
    </div>       
    </section>

</template>

<script>
import ComicCard from './ComicCard.vue';
import comicService from '../services/ComicService.js';
import marvelService from '../services/MarvelService.js';

export default {
    name: "new-comics-list",
    components: { 
        ComicCard 
    },
    data() {
        return {
            comics: []
            
            
        }
    },
	created() {
		// comicService.getComicsByCollectionId(9).then(response => {
		// this.$store.state.comics = response.data;
		// console.log("COMICS");
		// console.log(this.$store.state.comics);
            marvelService.getLatestReleases().then(response => {
                this.comics = response.data;
                response.data.forEach(element => {
                    console.log("ADDCOMIC CALLED WITH:")
                    console.log(element)
                    comicService.addComic(element);
            })
        });
	},
    
}
</script>

<style scoped>

.new-comics-text {
    margin-top: 4%;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.new-comics-list {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    margin-top: 2%;
}

</style>