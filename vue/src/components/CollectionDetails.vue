<template>
    <div class='container'>
        <h2>{{collection.collectionName}}</h2>
        <p>Public?</p>
        <label id="switch"><input type="checkbox" v-model="collection.public" /><span id="slider"></span></label>
    </div>
</template>



<style scoped>

</style>