components / SearchComics

<template>
  <div class="search-comics">
    <img class="image-container" src="../../public/star-wars.jpg" alt="star-wars image">
    <div class="search-container">
        <input class="search-box" type="text" placeholder="  Search" v-model="searchValue">

    </div>

  </div>
</template>

<script>
export default {
    name: "search-comics",
    components: {},
    data: () => ({
        searchValue: '',
    }),
};

</script>

<style scoped>

/* .search-comics {
    display: ;
} */

.image-container {
    width: 100%;
    height: 30em;
    object-fit: cover;
    object-position: 100% 70%;
    position: absolute;
}

.search-container {
    position: absolute;
    left: 29%;
    top: 63%;
}

.search-box {
    width: 50rem;
    height: 5rem;
    border-radius: 50px;

}
</style>