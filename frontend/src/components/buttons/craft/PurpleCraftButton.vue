<script lang="ts" setup>
import BaseButton from "~/components/buttons/craft/BaseCraftButton.vue";
</script>

<template>
  <BaseButton class="craft-button-purple text-white">
    <slot/>
  </BaseButton>
</template>

<style scoped>
.craft-button-purple {
  --bottom-background-color: #4A1CAC;
  --background-color: #7345E5;
  --border-top-right-color: #A264F2;
  --border-bottom-left-color: #8E49EB;
  --border-corner-color: #AA5CF3;
  --hover-background-color: #5D2CC6;
  --hover-border-top-right-color: #AE69EE;
  --hover-border-bottom-left-color: #9243E2;
  --hover-border-corner-color: #B35BF2;
  --active-background-color: #4A1CAC;
  --active-border-top-right-color: #A864E6;
  --active-border-bottom-left-color: #8B3CD8;
  --active-border-corner-color: #B059EF;
}
</style>