<script lang="ts" setup>
defineProps<{
  navbarPosition?: ("sticky" | "fixed")
}>()
</script>

<template>
  <div class="bg-neutral-950 text-white">
    <NuxtLoadingIndicator color="repeating-linear-gradient(to right,#8419D8 0%,#E65AFD 100%)"/>
    <div class="flex flex-col min-h-screen">
      <NavBar :position="navbarPosition"/>
      <div class="flex flex-1 overflow-hidden">
        <slot/>
      </div>
      <Footer/>
    </div>
  </div>
</template>