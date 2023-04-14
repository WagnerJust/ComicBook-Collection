<template>
	<section class="container">
		<div class="new-comics">
			<div>
				<img class="image-fit" src="../../public/marvelbannersmall.jpg" alt="Marvel Banner" />
			</div>
			<new-comics-list />
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