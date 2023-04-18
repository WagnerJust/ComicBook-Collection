<template>
	<section class="container">
		<div class="home">
			<div class="image-container">
				<!-- <img class="image-fit" src="https://w0.peakpx.com/wallpaper/691/675/HD-wallpaper-marvel-comics-marvel-logo.jpg" alt="Marvel iamge" /> -->
			</div>
			<div class="components">
				<new-comics-list />
				<characters-list />
				<aggregate-statistics/>
			</div>

		</div>
	</section>
</template>


<script>
import NewComicsList from '../components/NewComicsList.vue';
import CharactersList from '../components/CharactersList.vue';
import AggregateStatistics from '../components/AggregateStatistics.vue';

export default {
	name: "home",
	components: { 
		NewComicsList,
		CharactersList,
	AggregateStatistics 
	},
};
</script>


<style scoped>

.image-container {
    background-image: url('/public/marvel-title-1.png');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0%;
	height: 500px;
}

.components {
	margin-left: 12%;
	margin-right: 12%;
}
</style>
