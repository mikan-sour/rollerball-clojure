<script lang="ts">
import { defineComponent, ref, watch } from "vue";
import { useRouter, useRoute } from "vue-router";

import File from "../components/atoms/File.vue";
import Button from "../components/atoms/Button.vue";
import NewFolderModal from "../components/molecules/NewFolderModal.vue";

interface IDoc {
  name: string;
  parent: string;
  type: "folder" | "file";
  _id: string;
}
interface IAPIPostRes {
  result: IDoc;
}

interface IAPIGetRes {
  result: {
    bookmark: string;
    warning: string;
    docs: IDoc[];
  };
}

export default defineComponent({
  components: {
    File,
    Button,
    NewFolderModal,
  },
  setup() {
    const showModal = ref(false);

    const router = useRouter();
    const route = useRoute();

    function nextLevel(level: string) {
      const routeLevels = route.path.split("/").length;
      if (routeLevels > 4) {
        return;
      }
      const newRoute = `${route.path}/${level}`;
      router.push(newRoute);
    }

    const files = ref<IDoc[]>([]);

    const getObjects = async () => {
      try {
        const req = await fetch(
          `http://localhost:3001/tree?parent=${route.path}`
        );
        const res: IAPIGetRes = await req.json();
        files.value = res.result.docs;
      } catch (error) {
        console.error(error);
      }
    };

    const postObject = async (name: string) => {
      try {
        const req = await fetch(`http://localhost:3001/new`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            parent: route.path,
            type: "folder",
            name,
          }), 
        });

        const res:IAPIPostRes = await req.json();
        files.value = [...files.value, res.result]
        
      } catch (error) {
        console.error(error);
      }
    };

    async function handleAdd(fileName: string) {
      try {
        postObject(fileName);
      } catch (error) {
        console.error(error);
      }
    }

    function openModal() {
      showModal.value = true;
    }

    function closeModal() {
      showModal.value = false;
    }

    watch(() => route.params, async () => {
      try {
        await getObjects()
      } catch (error) {
        console.error(error);
      }
    }); 

    return {
      files,
      showModal,
      handleAdd,
      openModal,
      closeModal,
      nextLevel,
      getObjects,
    };
  },

  async mounted() {
    await this.getObjects();
  },

  
});
</script>

<template>
  <main>
    <NewFolderModal
      :showModal="showModal"
      :onSubmit="handleAdd"
      :onCloseModal="closeModal"
    />
    <section>
      <Button primary :onClick="openModal">ADD</Button>
    </section>
    <ul>
      <li v-for="(file, index) in files" :key="index">
        <File :file="file.name" :handleClick="nextLevel" />
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
  top: 0;
  right: 0;
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
