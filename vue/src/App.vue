<template>

	<div id="app">
		<the-header />
		<router-view class="router-view" />
	</div>

</template>


<script>
import TheHeader from './components/TheHeader.vue';

export default {
	components: { TheHeader }
}
</script>


<style>

@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;800;900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Oswald&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
    background-color: #ECF4FF;
}


</style>
