<template>
    <div class="main">
        <the-header />
        <new-comics-list />
    </div>
</template>


<script>
import TheHeader from '../components/TheHeader.vue';
import NewComicsList from '../components/NewComicsList.vue';

export default {
    name: 'my-collections',
    components: { 
        TheHeader,
        NewComicsList 
    },
}
</script>


<style scoped>

.home {
  margin-left: 12%;
  margin-right: 12%;

}

</style>