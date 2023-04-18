<template>
    <div class="card">
        <add-comic class="add-button" :comic="comic" v-if="this.$route.name != 'collection'" />
        <delete-comic :comic="comic" v-if="this.$route.name === 'collection'" />
        <div class="image-wrap">
            <router-link v-bind:to="{ name: 'comicdetails', params: { id: comic.comicId } }"><img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.imageURL + '/detail.jpg'" /></router-link>
            <h3 class="comic-series">{{ comic.seriesName }} #{{comic.issueNumber}}</h3>
            <p class="comic-creator">Writer: {{ comic.author }}</p>
            <p class="comic-creator">Penciler: {{ comic.artist }}</p>
            <p class="comic-publish-date">Published: {{ comic.publish_date }}</p>
        </div>
        
    </div>
</template>

<script>
import AddComic from '../components/AddComic.vue';
import DeleteComic from '../components/DeleteComic.vue'

export default {
    name: 'comic-card',
    components: {
        AddComic,
        DeleteComic
    },
    props: {
        comic: Object
    }
}
</script>

<style scoped>

.card {
    width: 18%;
    height: 25%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    text-align: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.add-button {
    text-align: end;
    margin-top: 5%;
    margin-bottom: 5%;
}

.image-wrap:hover {
    color: #ED1D24;
}

.comic-image {
    width: 100%;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    position: relative;
    transition: transform 250ms;
}

.comic-image:hover {
    transform: translateY(-10px);
}

.comic-series {
    font-weight: 600;
    margin-top: 1.5rem;
    text-align: left;
    font-size: 1.2rem;
}

.comic-creator {
    margin-top: 5px;
    text-align: left;
    font-family: 'Oswald', Helvetica, sans-serif;
    font-size: 1.1rem;
    font-weight: 500;
}

.comic-publish-date {
    text-align: left;
    margin-top: 5px;
    font-family: 'Oswald', Helvetica, sans-serif;
    font-size: 1rem;
    font-weight: 500;
}

</style>