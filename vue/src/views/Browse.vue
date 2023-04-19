<template>
  <div class="browse-main">
      <section class="page">
          <div class="search-main">
            <test-search />
          </div>

      </section>
  </div>
</template>

<script>

import TestSearch from '../components/TestSearch.vue';

export default {
    name: "browse",
    components: {
        TestSearch
    }

}
</script>

<style scoped>

.browse-main {
    min-height: 100vh;
}

.page {
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