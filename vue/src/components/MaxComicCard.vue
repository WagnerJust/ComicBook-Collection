<template>
    <div class="card-container">
        <div class="card">
            <p class="add" tag="button">Add</p>
            <h3 class="comic-series">{{ comic.seriesName }}</h3>
            <p class="publish-date">Published: {{ comic.publish_date }}</p>
            <p class="issue-number">Issue# {{ comic.issueNumber }}</p>
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