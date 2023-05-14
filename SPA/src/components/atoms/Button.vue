<template>
  <button :class="classes" @click="handleClick">
    <slot />
  </button>
</template>

<script setup lang="ts">
import { defineProps, computed } from 'vue'

const props = defineProps({
  primary: { type: Boolean, default: true },
  secondary: { type: Boolean, default: false },
  onClick: { type: Function, default: () => {} }
})

const classes = computed(() => ({
  'primary-button': props.primary,
  'secondary-button': props.secondary
}))

function handleClick() {
  props.onClick()
}
</script>

<style scoped>
    .primary-button {
        height: 3rem;
        width: 7rem;
        background-color: #5D7CFA;
        border:none;
        border-radius: 3px;
        box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
        color:#FFF;
        font-size: 14px;
        font-weight: 500;
        margin: 1rem;
        cursor: pointer;
    }

    .primary-button:hover {
        background-color: #329AEF;
        font-weight: 900;
    }

    .primary-button:active {
        background-color: rgba(255,20,147,.4);
        font-weight: 900;
    }
</style>