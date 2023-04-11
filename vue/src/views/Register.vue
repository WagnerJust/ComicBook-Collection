<template>
  <div id="container">
    <the-header id="nav" />
    <div class="home">
      <registration-form />
      <existing-user-sign-in />
    </div>
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

.home {
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>
