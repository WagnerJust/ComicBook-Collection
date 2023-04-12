<template>
    <div id="create-user-box">
        <div>
            <div id="welcome">Welcome Back!</div>
            <p>Not a Member?</p>
            <router-link :to="{ name: 'register' }" tag="button" class="register">SIGN UP</router-link>
        </div>
    </div>
</template>


<script>
export default {
    
}
</script>

<style scoped>

#create-user-box {
  display: flex;
  background: linear-gradient(#e66465, #fff0ed);
  height: 600px;
  width: 400px;
  align-self: center;
  justify-self: center;
  grid-area: register;
  border: 1px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 3rem;
  font-family: 'Montserrat', Helvetica, sans-serif;
}

.register {
  position: relative;
  bottom: -70px;
}

#welcome {
  font-size: 2.2rem;
  font-weight: 800;
  position: relative;
  top: -80px;
  color: white;
}

p {
  color: white;
  position: relative;
  bottom: -45px;
}

</style>