<template>
    <section>
        <div id="collection-banner"></div>
        <div class="container">
            <personal-collections />
            <public-collections />
        </div>
    </section>
</template>


<script>
import PersonalCollections from '../components/PersonalCollections.vue';
import PublicCollections from '../components/PublicCollections.vue';

export default {
    name: 'collections',
    components: {
        PersonalCollections,
        PublicCollections
    }
}
</script>


<style scoped>

.container {
    margin-left: 12%;
    margin-right: 12%;
}

#collection-banner {
    background-image: url('/public/marvel-panorama-3.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0% 70%;
	height: 450px;
}

</style>