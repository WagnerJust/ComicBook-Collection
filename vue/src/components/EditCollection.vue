<template>
    <div class='container'>
        <button id="edit-form-button" @click="showForm = true">Edit Collection</button>
            <form id="collection-form" name="collection-form" autocomplete="off" v-if="showForm">
                <div class="field">
                    <input id="collection-form-name-input" name="collection-form-name-input" type="text" placeholder= updateName() v-model="collection.collectionName" />
                </div>
                <div id="public-section" class="field">
                    <label id="public-text" for="checkbox">Public? </label>
                    <label id="switch"><input type="checkbox" v-model="collection.public" /><span id="slider"></span></label>
                </div>
                <div class="field">
                    <button id="save-collection-button" type="submit" @click="updateCollection()" >Update Collection</button>
                    <button id="reset-collection-button" type="reset" >Reset Form</button>              
                </div>
                <a id="cancel-button" type="reset" v-on:click="resetForm">Cancel</a> 
            </form>
    </div>
</template>


<script>
import collectionService from '../services/CollectionsService.js';

export default {
    name: "edit-collection",
    data() {
        return {
            showForm: false,
            collection: []
        }
    },
    computed: {

        updateName() {
            let collectionName = this.collection.collectionName
            return collectionName
        }

    },
    created() {

        collectionService.getCollectionByCollectionId(this.$route.params.id).then(response => {
            this.collection = response.data;
            console.log("TEST");
            console.log(this.collection)
        });

    },
    methods: {
        updateCollection() {
            let x = document.forms["collection-form"]["collection-form-name-input"].value;
            if (x == "") {
                return false;
            } else {
                console.log("TEST")
                console.log(this.collection)
                collectionService.updateCollection(this.collection).then(response => {
                    if (response.status === 201) {
                        this.$router.go("/collections")
                    }
                })                
            }
        },
        resetForm() {
            this.showForm = false;
            this.collection = {
                userId: this.$store.state.user.id,
                collectionId: '',
                public: false
            }
        }
    }
}
</script>


<style scoped>
    
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 1rem;
    text-align: left;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

#edit-form-button {
    width: 10rem;
    font-size: 1rem;
}


#show-form-button {
    width: 15rem;
}

.field {
    display: flex;
    justify-content: center;
    margin: 1rem;
}

#collection-form-name-input {
    height: 3rem;
    width: 25rem;
    font-size: 1.5rem;
    text-align: center;
    border: 2px solid black;
}

#collection-form-name-input:focus::placeholder {
    color: transparent;
}

input::placeholder {
    text-align: center;
}

#public-section {
    display: flex;
    align-items: center;
}

#public-text {
    font-size: 2rem;
    font-weight: bold;
    margin-right: 1rem;
}

button {
    cursor: pointer;
    border: 0;
    border-radius: 4px;
    font-weight: 600;
    margin: 1 10px;
    width: 8rem;
    padding: 10px 0;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
    transition: 0.4s;
    color: black;
    background-color: rgba(255, 255, 255, 1);
    border: 1px solid black;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

#reset-collection-button {
    background-color: rgba(236, 29, 36, 0.6);
}

#reset-collection-button:hover {
    background-color: rgba(236, 29, 36, 1);
}

button:hover {
    color: white;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    background-color: rgba(236, 29, 36, 1);
}

#save-collection-button {
    margin-right: 1rem;
    width: 9rem;
}

#cancel-button {
    display: flex;
    justify-content: center;
    cursor: pointer;
    margin-top: 1rem;
}

/* The toggle switch - this code was copied and slightly edited from: https://www.w3schools.com/howto/howto_css_switch.asp */

#switch {
    position: relative;
    display: inline-block;
    width: 60px;
    height: 34px;
}

#switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

#slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    -webkit-transition: .4s;
    transition: .4s;
}

#slider:before {
    position: absolute;
    content: "";
    height: 26px;
    width: 26px;
    left: 4px;
    bottom: 4px;
    background-color: white;
    -webkit-transition: .4s;
    transition: .4s;
}

input:checked + #slider {
    background-color: #23ce2b;
    box-shadow: 0 0 1px #23ce2b;
}

input:checked + #slider:before {
    -webkit-transform: translateX(26px);
    -ms-transform: translateX(26px);
    transform: translateX(26px);
}

</style>