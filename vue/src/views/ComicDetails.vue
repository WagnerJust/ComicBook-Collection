<template>
	<section class="container">
        <div class="card-container">
            <div class="comic-image-div">
                <img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.imageURL + '/detail.jpg'" >
            </div>
            <div class="comic-details">
                <h3 class="series-name">{{ comic.seriesName }}</h3>
                <p class="issue-number">Issue Number: {{ comic.issueNumber }}</p>
                <p class="writer">Writer: {{ comic.author }}</p>
                <p class="penciler">Penciler: {{ comic.artist }}</p>
                <div class="add-button">
                    <add-comic v-bind:comic="comic"/>
                </div>
            </div>
        </div>

	</section>
</template>

<script>
import AddComic from '../components/AddComic.vue';

export default {
	name: "new-comics",
    computed: {
        comic() {
            return this.$store.state.comics.find((c) => c.comicId === this.$route.params.id);
        }
    },
	components: {
        AddComic
	},
};
</script>

<style scoped>

.container {
	margin-left: 12%;
	margin-right: 12%;
}

.card-container {
    display: flex
}

.comic-details {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    font-size: 3vh;
    padding: 3rem;
    font-family: 'Montserrat', Helvetica, sans-serif;
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
    display: inline;
    margin-top: 5%;
    height: 5rem;
    width: 100%;
}

h3, p {
    margin: 1rem;
}

</style>