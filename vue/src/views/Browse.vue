<template>
  <div class="browse-main">

      <section>
          <search-comics />
      </section>
  </div>
</template>

<script>
import SearchComics from '../components/SearchComics.vue';

export default {
    name: "browse",
    components: {
        SearchComics,
    }
}
</script>

<style>

</style>