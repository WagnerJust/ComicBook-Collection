<template>
  <div id="container">
    <company-banner />
    <login-form />
    <div id="create-user-box">
      <div>
        <div id="welcome">Welcome Back!</div>
        <p>Not Registered?</p>
        <router-link :to="{ name: 'register' }" tag="button">SIGN UP</router-link>
      </div>
    </div>
  </div>
</template>


<script>
import CompanyBanner from '../components/CompanyBanner.vue';
import LoginForm from '../components/LoginForm.vue';

export default {
  name: "login",
  components: {
    CompanyBanner,
    LoginForm,
  },
};
</script>


<style scoped>

#container {
  background-color: #F5DE87;
  display: grid;
  grid-template-areas: 
  "banner banner"
  "loginForm register"
  "loginForm register";
  gap: 1rem;
  border: 5px solid black;
  height: 95vh;
}

#welcome {
  font-size: 3rem;
}

#create-user-box {
  display: flex;
  background-color: #BC545E;
  height: 80%;
  width: 50%;
  align-self: center;
  justify-self: center;
  grid-area: register;
  border: 5px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
}

@media (max-width: 1000px) {
  #container {
    display: grid;
    grid-template-areas:
    "banner"
    "loginForm"
    "register";
  }
}

</style>