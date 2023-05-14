<template>
  <div v-if="showModal" class="modal-overlay" @click="closeModal">
    <div class="modal" @click.stop>
      <div class="modal-header">
        <h3>{{ header }}</h3>
      </div>
      <div class="modal-body">
        <label for="folder-name">Folder Name:</label>
        <input id="folder-name" type="text" v-model="folderName" />
      </div>
      <div class="modal-footer">
        <Button :onClick="closeModal">Cancel</button>
        <Button :onClick="submitForm">Submit</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps, ref, watch, toRef } from 'vue'
import Button from "../atoms/Button.vue";

const props = defineProps({
  showModal: { type: Boolean },
  header: { type: String, default: 'New Folder' },
  onCloseModal: { type: Function },
  onSubmit: { type: Function }
})

const folderName = ref('')

function closeModal() {
  props.onCloseModal()
  folderName.value = ''
}

function submitForm() {
  props.onSubmit(folderName.value)
  closeModal()
}

const showModalRef = toRef(props, 'showModal');

watch(showModalRef, (value) => {
    console.log("y", value)
      if (value) {
        // add class to disable scrolling
        document.body.classList.add('no-scroll');
      } else {
        // remove class to re-enable scrolling
        document.body.classList.remove('no-scroll');
      }
})

</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.modal {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  text-align: center;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

h3 {
  margin-left:1rem;
  font-size:22px;
}

.modal-header {
  height: 20%;
  width:100%;
  font-size: 1.5rem;
  font-weight: bold;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  background-color: #5D7CFA;
  color:#fff;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;

}

.modal-body {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
}

.modal-body label {
  display: block;
  color:#111;
  align-self: baseline;
  padding-left: 8%;
}

.modal-body input[type="text"] {
  justify-self: center;
  width: 80%;
  margin: .5rem;
  padding: 10px;
  border-radius: 3px;
  border: none;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
}

.modal-footer {
  display: flex;
  flex-direction: row;
  margin-left: 9rem;
}

.modal-footer button {
    margin: 1rem .25rem;
}

</style>

<style>
.no-scroll {
  overflow: hidden !important;
}
</style>