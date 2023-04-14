<template>
    <div class="container">
        <button v-on:click.prevent="showCollections = true">Add</button>
        <div class="collection-box" v-if="showCollections === true">Collections go here</div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            showCollections: false,
        }
    }
}
</script>


<style scoped>

.container {
    display: flex;
    justify-content: flex-end;
    margin: 1rem;
}

.collection-box {
    margin-top: 1rem;
    background-color: white;
}


</style>