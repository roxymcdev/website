<template>
  <NuxtLink class="craft-button select-none cursor-pointer border-craft-outline font-minecraft-five">
    <span class="craft-button-content">
      <slot/>
    </span>
  </NuxtLink>
</template>

<style scoped>
.craft-button {
  --pixel-size: 4px;
  background-color: var(--bottom-background-color);
  border-width: var(--pixel-size);
  padding: 0 0 calc(2 * var(--pixel-size)) 0;
  margin-top: calc(0px - 2 * var(--pixel-size));
}

.craft-button:active {
  padding: 0;
  margin-top: 0;
}

.craft-button:focus-visible {
  z-index: 1;
  outline: white solid var(--pixel-size);
  box-shadow: none;
}

.craft-button-content {
  --top-right-color: var(--border-top-right-color);
  --bottom-left-color: var(--border-bottom-left-color);
  --corner-color: var(--border-corner-color);

  position: relative;
  display: block;
  background-color: var(--background-color);
  box-shadow: -2px -2px 0 2px var(--top-right-color), 2px 2px 0 2px var(--bottom-left-color), 0 0 0 4px var(--corner-color);
  padding: calc(1.5 * var(--pixel-size)) calc(10px + 1.5 * var(--pixel-size));
  margin: var(--pixel-size);
  font-size: calc(4 * var(--pixel-size));
}

.craft-button:hover .craft-button-content {
  --top-right-color: var(--hover-border-top-right-color);
  --bottom-left-color: var(--hover-border-bottom-left-color);
  --corner-color: var(--hover-border-corner-color);

  background-color: var(--hover-background-color);
}

.craft-button:active .craft-button-content {
  --top-right-color: var(--active-border-top-right-color);
  --bottom-left-color: var(--active-border-bottom-left-color);
  --corner-color: var(--active-border-corner-color);

  background-color: var(--active-background-color);
}
</style>