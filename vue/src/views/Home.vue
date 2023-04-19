<template>
	<section class="container">
		<div class="home">
			<aggregate-statistics/>
			<div class="image-container">
			</div>
			<div class="components">
				<public-collections />
				<characters-list />
			
			</div>

		</div>
		<the-footer />
	</section>
</template>


<script>
import PublicCollections from '../components/PublicCollections.vue';
import CharactersList from '../components/CharactersList.vue';
import AggregateStatistics from '../components/AggregateStatistics.vue';
import TheFooter from '../components/TheFooter.vue';

export default {
	name: "home",
	components: { 
        PublicCollections,
		CharactersList,
		AggregateStatistics,
		TheFooter
	},
};
</script>


<style scoped>

aggregate-statistics{
	display: block;
	margin: 10,10,10,10;
	padding: 10,10,10,10;

}

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
