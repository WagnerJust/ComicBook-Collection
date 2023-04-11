<template>
    <div id="create-user-box">
        <div>
            <div id="welcome">Welcome Back!</div>
            <p>Not Registered?</p>
            <router-link :to="{ name: 'register' }" tag="button">SIGN UP</router-link>
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
  background-color: #BC545E;
  height: 300px;
  width: 300px;
  align-self: center;
  justify-self: center;
  grid-area: register;
  border: 5px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 3rem;
}

#welcome {
  font-size: 3rem;
}

</style>