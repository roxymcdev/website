<script lang="ts" setup>
defineProps<{
  pattern?: string
  placeholder?: string
  required?: boolean
}>()
</script>

<template>
  <div class="w-full flex flex-col border-4 border-craft-outline before:h-2 before:w-full before:bg-[#242425]">
    <input :pattern="pattern" :placeholder="placeholder" :required="required" autocomplete="off"
           class="h-full min-w-full w-0 px-3 pb-1 bg-[#313233] font-minecraft-seven text-lg truncate outline-none"
           type="text"
           @input="e => $emit('input', e)">
  </div>
</template>