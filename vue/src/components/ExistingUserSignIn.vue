<template>
    <div id="sign-in-box">
        <div>
            <div id="welcome">Hello, Friend!</div>
            <p>Have an account?</p>
            <router-link :to="{ name: 'login' }" tag="button" class="register">SIGN IN</router-link>
        </div>
    </div>
</template>


<script>
export default {
    
}
</script>


<style scoped>

#sign-in-box {
  display: flex;
  background: linear-gradient(#e66465, #fff0ed);
  height: 600px;
  width: 400px;
  align-self: center;
  justify-self: center;
  grid-area: signInBox;
  border: 1px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 3rem;
  font-family: 'Montserrat', Helvetica, sans-serif;
}

.register {
  position: relative;
  bottom: -90px;
}

#welcome {
  font-size: 2.2rem;
  font-weight: 800;
  position: relative;
  top: -110px;
  color: white;
}

p {
  color: white;
  position: relative;
  bottom: -70px;
}

</style>