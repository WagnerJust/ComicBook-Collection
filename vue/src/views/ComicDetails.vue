<template>
	<section class="container">
        <div class="card-container">
            <div class="comic-image-div">
                <img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.imageURL + '/detail.jpg'" >
            </div>
            <div>
                <div class="comic-details">
                    <h3 class="series-name">{{ comic.seriesName }}</h3>
                    <p class="issue-number">#{{ comic.issueNumber }}</p>
                    <p class="writer">Writer: {{ comic.author }}</p>
                    <p class="penciler">Penciler: {{ comic.artist }}</p>
                    <p class="penciler">UPC: {{ comic.upc }}</p>
                    
                </div>
            </div>
        </div>
	</section>
</template>

<script>
import comicService from '../services/ComicService.js';

export default {
	name: "comic-details",
    data() {
        return {
            comic: [],
        }
    },
    created() {
        
        comicService.getComicByUpc(this.$route.params.id).then(response => {
            this.comic = response.data;
        });
        
    }
}
</script>

<style scoped>

.container {
	margin-left: 12%;
	margin-right: 12%;
}

.card-container {
    display: flex;
}

.comic-details {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: left;
    font-size: 3vh;
    padding: 3rem;
    font-family: 'Montserrat', Helvetica, sans-serif;
    margin-left: 100px;
}

.comic-image-div {
    display: flex;
    margin-top: 2%;
}

.comic-image {
    height: 75vh;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
}

.add-button {
    display: flex;
    margin-top: 1px;
    margin-bottom: 5%;
}

h3, p {
    margin: 1rem;
}

</style>