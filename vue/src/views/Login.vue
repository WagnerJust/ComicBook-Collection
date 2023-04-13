<template>
  <div id="container" class="container">
    <div class="main">
      <login-form />
    </div>
    <!-- <img class="image-fit" src="https://mightymega.com/wp-content/uploads/2013/05/marvel_panoramic_posters_2.jpg" alt="Marvel image" /> -->
  </div>
</template>


<script>
import LoginForm from '../components/LoginForm.vue';

export default {
  name: "login",
  components: {
    LoginForm
  },
};
</script>


<style scoped>

.container {
  background-image: url('/public/she-hulk.png');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: right;
  opacity: 0.9;
  height: 100vh;
}

.main {
  display: flex;
  align-items: center;
  justify-content: center;
}

.image-fit {
  display: flex;
  justify-content: space-evenly;
  object-fit: cover;
  margin: auto;
  margin-top: 3%;
}

</style>