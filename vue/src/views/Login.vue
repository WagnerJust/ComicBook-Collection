<template>
	<section class="container">
		<div class="main">
			<login-form />
		</div>
	</section>
</template>


<script>
import LoginForm from '../components/LoginForm.vue';

export default {
  name: "login",
  components: {
    LoginForm
  }
};
</script>


<style scoped>

.container {
	background-image: url('/public/she-hulk.png');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: bottom;
	opacity: 0.9;
	height: 84.5vh;
}

.main {
	display: flex;
	align-items: center;
	justify-content: center;
}

</style>