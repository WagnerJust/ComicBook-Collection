<template>
  <section class="container">
    <div class="main">
      <registration-form />
    </div>
  </section>
</template>


<script>
import RegistrationForm from '../components/RegistrationForm.vue';

export default {
	name: 'register',
	components: {
		RegistrationForm
	}
};
</script>


<style scoped>

.container {
	background-image: url('/public/x-men-1.png');
	background-repeat: no-repeat;
	background-size: cover;
	background-position: bottom;
	opacity: 0.9;
	height: 84vh;
}

.main {
	display: flex;
	align-items: center;
	justify-content: center;
}

</style>
