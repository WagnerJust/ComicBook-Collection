<template>
  <div>
    <div class="collections-list" v-for="collection in collections" :key="collection.collectionId">
        {{collection.collectionName}}
    </div>
</div>
</template>

<script>
import CollectionsService from '../services/CollectionsService'
export default {
    name: "my-collections",
    data(){
        return{
            collections: [],
            currentUserId: this.$store.state.user.id
        }

    },
    created(){
        CollectionsService.listByUserId(this.currentUserId)
        .then(response => {
            this.collections = response.data;
        })
        .catch(error => {
            console.error(error + "no collections")
        })
    }

}
</script>

<style>

</style>