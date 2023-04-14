<template>
	<section class="container">
		<div class="home">
			<div>
				<img class="image-fit" src="https://w0.peakpx.com/wallpaper/691/675/HD-wallpaper-marvel-comics-marvel-logo.jpg" alt="Marvel iamge" />
			</div>
			<new-comics-list />
			<characters-list />
		</div>
	</section>
</template>


<script>
import NewComicsList from '../components/NewComicsList.vue';
import CharactersList from '../components/CharactersList.vue';

export default {
	name: "home",
	components: { 
		NewComicsList,
		CharactersList 
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

</style>
