<template>
	<section class="container">
		<div class="new-comics">
			<div class="image-fit">
				<!-- <img class="image-fit" src="../../public/marvelbannersmall.jpg" alt="Marvel Banner" /> -->
			</div>
			<div class="components">
				<new-comics-list />
			</div>

		</div>
	</section>
</template>

<script>
import NewComicsList from '../components/NewComicsList.vue';

export default {
	name: "new-comics",
	components: { 
		NewComicsList,
	},
};
</script>

<style scoped>

.components {
	margin-left: 12%;
	margin-right: 12%;
}

.image-fit {
    background-image: url('/public/marvelbannersmall.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0% 0%;
	height: 450px;
}

</style>