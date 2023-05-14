<script lang="ts">
import { defineComponent, ref } from "vue";
import { useRouter, useRoute } from 'vue-router'

import File from "../components/atoms/File.vue";
import Button from "../components/atoms/Button.vue";
import NewFolderModal from "../components/molecules/NewFolderModal.vue";

export default defineComponent({
  components: {
    File,
    Button,
    NewFolderModal
  },
  setup() {
    const showModal = ref(false);

    const router = useRouter()
    const route = useRoute()

    function nextLevel(level:string){
        const routeLevels = route.path.split("/").length;
        if(routeLevels > 4) {
            return;
        }
        const newRoute = `${route.path}/${level}`
        router.push(newRoute)
    }

    const files = ref([
      "one_file",
      "two_file",
      "three_file",
      "four_file",
      "five_file",
      "six_file",
      "seven_file",
      "eight_file",
      "nine_file",
      "ten_file",
      "eleven_file",
      "twelve_file",
    ]);

    function handleAdd(fileName:string) {
      files.value = [fileName, ...files.value]
      console.log(files.value)
    }

    function openModal(){
        showModal.value = true;
    }

    function closeModal(){
        showModal.value = false;
    }

    return {
      files,
      showModal,
      handleAdd,
      openModal,
      closeModal,
      nextLevel
    };
  },
});
</script>

<template>
  <main>
    <NewFolderModal 
        :showModal="showModal" 
        :onSubmit="handleAdd"
        :onCloseModal="closeModal"/>
    <section>
      <Button primary :onClick="openModal">ADD</Button>
    </section>
    <ul>
      <li v-for="(file, index) in files" :key="index">
        <File :file="file" :handleClick="nextLevel"/>
      </li>
    </ul>
  </main>
</template>

<style scoped>
main {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
}

section {
    width: 80%;
    position: absolute;
    top:0;right:0;
    display: flex;
    flex-direction: row-reverse;
    align-items: center;
    z-index: 2;
}

ul {
  position: absolute;
  right: 10px;
  top: 10%;
  bottom: 0;
  width: 80%;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  align-items: center;
}

li {
  list-style: none;
}
</style>
