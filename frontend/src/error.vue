<script lang="ts" setup>
import Content from "~/components/layout/Content.vue";
import NavButton from "~/components/buttons/nav/NavButton.vue";

const error = useError();

useHead({
  title: `RoxyMC - ${error.value.statusCode}`
})
</script>

<template>
  <NuxtLayout navbar-position="fixed">
    <div class="w-full flex items-center">
      <Content>
        <div class="px-2">
          <div class="flex flex-col items-center gap-2 h-full max-w-full">
            <p class="text-7xl font-minecraft-ten">{{ error.statusCode }}</p>
            <p class="font-minecraft-seven text-xl break-all">{{ error.message }}</p>
            <NavButton as="span" class="font-minecraft-five" to="/">Go back home</NavButton>
          </div>
        </div>
      </Content>
    </div>
  </NuxtLayout>
</template>