<template>
	<section class="container">
        <h2 class ="comic-details-text">Comic Details</h2>
        <img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.image + '/portrait_xlarge.jpg'" >
		<div class="comic-details">
            <h3> {{ comic.title }}</h3>
            <p> Issue Number: {{ comic.issueNumber }}</p>
            <p> Writer: {{ comic.writer }}</p>
            <p> Penciler: {{ comic.penciler }}</p>
			<comic-card v-bind:comic="comic" v-for="comic in comics" v-bind:key="comic.comicId"/>
            <Details class="details-pic" v-bind:comic="comic"/>
		</div>
	</section>
</template>

<script>
import ComicCard from '../components/ComicCard.vue';
import Details from '../components/Details.vue';

export default {
	name: "new-comics",
    computed: {
        comic() {
            return this.$store.state.comics.find((c) => c.comicId === this.$route.params.id);
        }
    },
	components: { 
		ComicCard,
        Details
	},
};
</script>

<style scoped>

.container {
	margin-left: 12%;
	margin-right: 12%;
}

.image-fit {
  height: 500px;
  width: 100%;
  object-fit: cover;
  margin: auto;
  margin-top: 3%;
}

.comic-details-text {
    font-size: 8vh;
    margin-top: 4%;
    margin: 4%;
    text-align: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.comic-details {
    font-size: 3vh;
    margin-top: 4%;
    margin: 4%;
    text-align: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
    float: right;
}

.comic-image {
    width: 40%;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    position: relative;
    transition: transform 250ms;
}

.details-pic{
    margin-top: 10%;
    align-content: center;
}

</style>