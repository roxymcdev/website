<script lang="ts" setup>
import type {PlayerStats} from "~/player/PlayerStats";

defineProps<{
  playerStats: PlayerStats
}>()
</script>

<template>
  <NuxtLink
      :to="`/player-stats/${playerStats.uniqueId}`"
      class="flex flex-col items-center border-4 border-craft-outline w-full sm:max-w-[18.5rem] py-4 hover:bg-neutral-900">
    <div class="flex items-center gap-1 px-[2px] py-px bg-[#ffffff10] mb-2">
      <GroupTag :group="playerStats.group"/>
      <p class="font-minecraft-seven text-xl mb-0.5 leading-3">{{ playerStats.name }}</p>
    </div>
    <div>
      <img :alt=playerStats.name :src="`https://vzge.me/full/288/${playerStats.uniqueId}.png?no=shadow`" class="h-72">
    </div>
  </NuxtLink>
</template>