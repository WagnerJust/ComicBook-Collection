<template>
  <div id="container" class="container">
    <the-header id="nav" />
    <div class="main">
      <login-form />
      <new-user-signup />
    </div>
    <!-- <img class="image-fit" src="https://mightymega.com/wp-content/uploads/2013/05/marvel_panoramic_posters_2.jpg" alt="Marvel image" /> -->
  </div>
</template>


<script>
import TheHeader from '../components/TheHeader.vue';
import LoginForm from '../components/LoginForm.vue';
import NewUserSignup from '../components/NewUserSignup.vue';


export default {
  name: "login",
  components: {
    TheHeader,
    LoginForm,
    NewUserSignup
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