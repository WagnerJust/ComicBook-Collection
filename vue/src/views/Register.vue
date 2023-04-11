<template>
  <div id="container">
    <company-banner />
    <registration-form />
    <div id="sign-in-box">
      <div>
        <div id="welcome">Hello, Friend!</div>
        <p>Have an account?</p>
        <router-link :to="{ name: 'login' }" tag="button">SIGN IN</router-link>
      </div>
    </div>
  </div>
</template>


<script>
import CompanyBanner from '../components/CompanyBanner.vue';
import RegistrationForm from '../components/RegistrationForm.vue';

export default {
  name: 'register',
  components: {
    CompanyBanner,
    RegistrationForm
  },

};
</script>


<style scoped>

#container {
  background-color: #F5DE87;
  display: grid;
  grid-template-areas: 
  "banner banner"
  "registrationForm signInBox"
  "registrationForm signInBox";
  gap: 1rem;
  border: 5px solid black;
  height: 95vh;
  justify-items: center;
}

#welcome {
  font-size: 3rem;
}

#sign-in-box {
  display: flex;
  background-color: #BC545E;
  height: 80%;
  width: 50%;
  align-self: center;
  justify-self: center;
  grid-area: signInBox;
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
    "registrationForm"
    "signInBox";
  }
}
</style>
