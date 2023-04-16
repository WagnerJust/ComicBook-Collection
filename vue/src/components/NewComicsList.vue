<template>
    <section>
    <h2 class="new-comics-text">New Comics</h2>
    <div class="new-comics-list" >
        <comic-card v-bind:comic="comic" v-for="comic in comics" v-bind:key="comic.comicId"/>
    </div>       
    </section>

</template>

<script>
import ComicCard from './ComicCard.vue';
import comicService from '../services/ComicService.js';

export default {
    name: "new-comics-list",
	data () {
		return {
			comics: []
		}
	},
    components: { 
        ComicCard 
    },
	created() {
		comicService.getComicsByCollectionId(9).then(response => {
		this.comics = response.data;
		console.log("COMICS");
		console.log(this.comics);
        });
	}
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