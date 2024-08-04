<script lang="ts" setup>
useSeoMeta({
  titleTemplate: (chunk) => {
    return chunk ? `RoxyMC - ${chunk}` : "RoxyMC"
  },
  ogTitle: "RoxyMC",
  description: "Official RoxyMC Network Minecraft server website.",
  ogDescription: "Official RoxyMC Network Minecraft server website.",
  twitterDescription: "Official RoxyMC Network Minecraft server website."
})

useHead({
  htmlAttrs: {
    lang: "en"
  }
})
</script>

<template>
  <NuxtPage/>
</template>
