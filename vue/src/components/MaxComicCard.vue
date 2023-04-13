<template>
    <div class="card-container">
        <div class="card">
            <h3 class="comic-series">{{ comic.seriesName }}</h3>
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

<style>


</style>