<template>
  <div id="container" class="container">
    <the-header id="nav" />
    <div class="main">
      <existing-user-sign-in />
      <registration-form />
    </div>
    <!-- <img class="image-fit" src="https://mightymega.com/wp-content/uploads/2013/05/marvel_panoramic_posters_1.jpg" alt="Marvel image" /> -->
  </div>
</template>


<script>
import TheHeader from '../components/TheHeader.vue';
import RegistrationForm from '../components/RegistrationForm.vue';
import ExistingUserSignIn from '../components/ExistingUserSignIn.vue';

export default {
  name: 'register',
  components: {
    TheHeader,
    RegistrationForm,
    ExistingUserSignIn
  }
};
</script>


<style scoped>

.container {
  background-image: url('/public/x-men.png');
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
