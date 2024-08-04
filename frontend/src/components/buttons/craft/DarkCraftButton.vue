<script lang="ts" setup>
import BaseButton from "~/components/buttons/craft/BaseCraftButton.vue";
</script>

<template>
  <BaseButton class="craft-button-dark text-white">
    <slot/>
  </BaseButton>
</template>

<style scoped>
.craft-button-dark {
  --bottom-background-color: #313233;
  --background-color: #48494A;
  --border-top-right-color: #6D6D6E;
  --border-bottom-left-color: #5A5B5C;
  --border-corner-color: #7B7B7C;
  --hover-background-color: #58585A;
  --hover-border-top-right-color: #79797B;
  --hover-border-bottom-left-color: #68686A;
  --hover-border-corner-color: #868688;
  --active-background-color: #313233;
  --active-border-top-right-color: #5A5B5C;
  --active-border-bottom-left-color: #454647;
  --active-border-corner-color: #6A6B6C;
}
</style>