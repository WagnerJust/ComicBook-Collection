<template>
    <section>
        <div id="collection-banner"></div>
        <div class="container">
            <personal-collections />
        </div>
    </section>
</template>


<script>
import PersonalCollections from '../components/PersonalCollections.vue';


export default {
    name: 'collections',
    components: {
        PersonalCollections
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