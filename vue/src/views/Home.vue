<template>
<section>
  <the-header />
  <div class="home">
    
    <div>
        <img class="image-fit" src="https://w0.peakpx.com/wallpaper/691/675/HD-wallpaper-marvel-comics-marvel-logo.jpg" alt="Marvel iamge" />
    </div>
    
    <new-comics-list />
  </div>
</section>

</template>

<script>
import NewComicsList from '../components/NewComicsList.vue';
import TheHeader from '../components/TheHeader.vue';

export default {
  name: "home",
  components: { 
    TheHeader,
    NewComicsList 
  },
};
</script>

<style scoped>


.home {
  margin-left: 12%;
  margin-right: 12%;

}

.image-fit {
  height: 500px;
  width: 100%;
  object-fit: cover;
  margin: auto;
  margin-top: 3%;
}


</style>
