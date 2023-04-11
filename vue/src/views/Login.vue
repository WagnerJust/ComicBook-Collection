<template>
  <div id="container">
    <the-header id="nav" />
    <div class="main">
      <login-form />
      <new-user-signup />
    </div>
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

.main {
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>