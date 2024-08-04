<script lang="ts" setup>
import {DisclosureButton} from "@headlessui/vue";

withDefaults(defineProps<{
  as?: Component | string
}>(), {
  as: DisclosureButton
})
</script>

<template>
  <NuxtLink class="group text-sm font-medium">
    <component :is="as" class="flex w-full">
      <span
          class="group-active:shadow-[0px_4px_0px_0px_currentColor] group-hover:shadow-[0px_4px_0px_0px_currentColor]">
        <slot/>
      </span>
    </component>
  </NuxtLink>
</template>