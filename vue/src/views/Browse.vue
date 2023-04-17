<template>
  <div class="browse-main">
      <section class="search-main">
          <!-- <search-comics /> -->
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

.browse-main {
    background-image: url('/public/star-wars.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0% 70%;
	height: 450px;
}

.search-main {
    margin-left: 12%;
	margin-right: 12%;
}

</style>