<template>
    <div class="card">
        <p class="add" tag="button" >Add</p>
        <div class="image-wrap">
            <img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.image + '/portrait_xlarge.jpg'" />
            <h3 class="comic-title">{{ comic.title }}</h3>
            <p class="comic-creator">{{ comic.writer }}, {{ comic.penciler }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'comic-card',
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
.comic-creator {
    margin-top: 5px;
    text-align: left;
    font-family: 'Oswald', Helvetica, sans-serif;
    font-size: 1rem;
    font-weight: 500;
}

.comic-title {
    font-weight: 600;
    margin-top: 1.5rem;
    text-align: left;
    font-size: 1.1rem;
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

.add {
    text-align: end;
    margin-bottom: 5%;
}

</style>