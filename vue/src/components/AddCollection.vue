<template>
    <div class="container">
        <button id="show-form-button" v-if="showForm === false" v-on:click.prevent="showForm = true">
            - Add New Collection -
        </button>
        <form class="collection-form" name="collection-form" autocomplete="off" v-if="showForm === true">
            <div class="field">
                <input required id="collection-form-name-input" name="collection-form-name-input" type="text" placeholder="Collection Name" v-model="collection.collectionName" onInvalid="this.setCustomValidity('Your collection must have a name.')" oninput="setCustomValidity('')" />
            </div>
            <div id="public-section" class="field">
                <label id="public-text" for="checkbox">Public? </label>
                <input id="switch" type="checkbox" v-model="collection.public" /><label id="testLabel" for="switch">Toggle</label>
            </div>
            <div class="field">
                <button id="save-collection-button" type="submit" v-on:click="saveCollection()">Save Collection</button>
                <button id="reset-collection-button" type="reset" >Reset Form</button>              
            </div>
            <a id="cancel-button" v-on:click="showForm = false">Cancel</a> 
        </form>
    </div>
</template>

<script>
import collectionService from '../services/CollectionsService.js';

export default {
    name: "add-collection",
    data() {
        return {
            showForm: false,
            collection: {
                userId: this.$store.state.user.id,
                collectionId: '',
                public: false
            }
        }
    },
    methods: {
        saveCollection() {
            let x = document.forms["collection-form"]["collection-form-name-input"].value;
            if (x == "") {
                return false;
            } else {
                collectionService.createCollection(this.collection).then(response => {
                    if (response.status === 201) {
                        this.$router.go("/collections")
                    }
                })                
            }
        }
    }
}
</script>

<style scoped>

form-text validate[required] form-validation-error {
    position: left;
}

.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 1rem;
    text-align: left;
    font-family: 'Montserrat', Helvetica, sans-serif;
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
}

#cancel-button {
    display: flex;
    justify-content: center;
    cursor: pointer;
    margin-top: 1rem;
}

input[type=checkbox]{
	height: 0;
	width: 0;
	visibility: hidden;
}

#testLabel {
	cursor: pointer;
	text-indent: -9999px;
	width: 4.2rem;
	height: 2rem;
	background: grey;
	display: block;
	position: relative;
    border: 3px solid black;
}

label:after {
	content: '';
	position: absolute;
	top: 0px;
	left: 0px;
	width: 2rem;
	height: 1.65rem;
	background: #fff;
	transition: 0.2s;
}

input:checked + #testLabel {
	background: #10e710;
}

input:checked + #testLabel:after {
	left: calc(100%);
	transform: translateX(-100%);
}

</style>