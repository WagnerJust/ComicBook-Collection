<template>
  <section>
      <div class="new-comics-list">
          <comic-card v-bind:comic="comic" v-for="comic in comics" v-bind:key="comic.comicId" />
      </div>
  </section>
</template>

<script>
import ComicCard from './ComicCard.vue'

export default {
  name: "new-comics-list",
  computed: {
      comics() {
          return this.$store.state.comics
      }
  },
  components: { ComicCard },

}
</script>

<style>
.new-comics-list {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>