<template>
  <div class="browse-main">

      <section>
          <!-- <search-comics /> -->
            <img class="image-container" src="../../public/star-wars.jpg" alt="star-wars image">
          <test-search />
      </section>
  </div>
</template>

<script>
// import SearchComics from '../components/SearchComics.vue';
import TestSearch from '../components/TestSearch.vue';

export default {
    name: "browse",
    components: {
        // SearchComics,
        TestSearch
    }
}
</script>

<style>
.image-container {
    width: 100%;
    height: 30em;
    object-fit: cover;
    object-position: 100% 70%;
    position: absolute;
}
</style>