<template>
  <div class="card">
      <h3 class="comic-title">{{ comic.title }}</h3>
      <img class="comic-image" v-bind:src="comic.image + 'portrait_xlarge.jpg'" />
      <h4 class="comic-creator">{{ comic.creator.role + ', ' + comic.creator.name}}</h4>
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
.card {
    width: 200px;
    height: 300px;
    margin: 20px;
}

.comic-image {
    width: 80%;
}
</style>