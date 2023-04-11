<template>
    <div class="card">
        <img class="comic-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + comic.image + '/portrait_xlarge.jpg'" />
        <h3 class="comic-title">{{ comic.title }}</h3>
        <p class="comic-creator">{{ comic.writer }}, {{ comic.penciler }}</p>
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

<style>
.card {
    width: 18%;
    height: 25%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    text-align: center;
}
.comic-creator {
    margin-top: 5px;
}

.card:hover {
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

</style>