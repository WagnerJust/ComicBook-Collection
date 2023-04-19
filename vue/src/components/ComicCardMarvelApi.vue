<template>
    <div class="card">
        <add-comic-marvel-api class="add-button" :comic="comic"  v-if="$store.state.token != ''" />
        <div class="image-wrap">
            <router-link v-bind:to="{ name: 'comicdetails', params: { id: comic.upc } }"><img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.imageURL + '/detail.jpg'" /></router-link>
            <h3 class="comic-series">{{ comic.seriesName }} #{{comic.issueNumber}}</h3>
            <p class="comic-creator" v-if="comic.author != null" >Writer: {{ comic.author }}</p>
            <p class="comic-creator" v-if="comic.artist != null" >Penciler: {{ comic.artist }}</p>
            <p class="comic-publish-date" v-if="comic.publish_date != null" >Published: {{ comic.publish_date }}</p>
        </div>
        
    </div>
</template>

<script>
import AddComicMarvelApi from '../components/AddComicFromApi.vue';

export default {
    name: 'comic-card',
    components: {
        AddComicMarvelApi
    },
    props: {
        comic: Object
    }
}
</script>

<style scoped>

.card {
    width: 18%;
    margin-bottom: 1rem;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    text-align: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.add-button {
    text-align: end;
    margin-top: 5%;
    margin-bottom: 5%;
}

.image-wrap {
    align-self: flex-start;
    justify-content: flex-start;
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