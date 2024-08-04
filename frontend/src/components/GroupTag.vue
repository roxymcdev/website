<script lang="ts" setup>
defineProps<{
  group: string | undefined
}>()
</script>

<template>
  <img :src="`/images/group-tags/${group?.toLowerCase() || 'default'}_tag.png`" alt=""
       class="image-pixelated h-[18px]"/>
</template>