<script lang="ts" setup>
import Content from "~/components/layout/Content.vue";
import {useLazyAsyncData} from "#app";
import type {PlayerStats} from "~/player/PlayerStats";
import {$fetch} from "ofetch";
import PlayerCard from "~/components/player-stats/PlayerCard.vue";
import SpinningWheel from "~/components/SpinningWheel.vue";

const apiBase = useRuntimeConfig().public.apiBase

useHead({
  title: "Player Stats"
})

const pageArea = ref<HTMLDivElement | null>(null)
const fetching = ref<boolean>(false)
const searchInput = ref<string>("")
let timeoutId: number | null = null;
let totalCount: number | null = null

const debounce = (func: () => void, wait: number) => {
  if (timeoutId) {
    clearTimeout(timeoutId);
  }

  timeoutId = window.setTimeout(func, wait);
}

const {
  execute,
  status,
  data: playersStats
} = await useLazyAsyncData(async () => {
  const response = await $fetch.raw(`${apiBase}/player-stats?search=${searchInput.value}`)

  totalCount = parseInt(response.headers.get("X-Total-Count") || response._data.length.toString())

  return response._data
}, {
  server: false,
  transform: (data) => data as PlayerStats[],
})

const onScroll = () => {
  if (!pageArea.value) return
  if (window.scrollY + window.innerHeight < pageArea.value.offsetHeight + pageArea.value.offsetTop) return;

  debounce(async () => {
    const offset = getOffset()
    if (totalCount && offset >= totalCount) return

    fetching.value = true
    const fetch = await $fetch(`${apiBase}/player-stats?search=${searchInput.value}&offset=${getOffset()}`) as PlayerStats[];
    fetching.value = false

    playersStats.value?.push(...fetch)
  }, 100)
}

onMounted(() => {
  window.addEventListener("scroll", onScroll)
})

onUnmounted(() => {
  window.removeEventListener("scroll", onScroll)
})

const input = (e: InputEvent) => {
  const input = (e.target as HTMLInputElement).value
  debounce(() => search(input), 1000)
}

const search = (input: string) => {
  searchInput.value = input
  execute()
}

const getOffset = (): number => status.value === "success" ? playersStats.value?.length || 0 : 0

const isFetching = (): boolean => !playersStats.value || fetching.value
</script>

<template>
  <NuxtLayout>
    <div ref="pageArea" class="w-full">
      <Content>
        <div id="player-stats">
          <TitledLine>
            <TextField class="min-w-64 sm:min-w-96" placeholder="Search for a player" @input="input"/>
          </TitledLine>
          <div class="px-2">
            <p class="text-lg font-minecraft-seven mb-8">Search results for: {{ searchInput }}</p>
            <ClientOnly>
              <div class="flex flex-col gap-8">
                <div v-if="playersStats" class="flex flex-wrap gap-4">
                  <PlayerCard v-for="stats in playersStats" :player-stats="stats"/>
                </div>
                <div v-if="isFetching()" class="flex justify-center">
                  <SpinningWheel/>
                </div>
              </div>
            </ClientOnly>
          </div>
        </div>
      </Content>
    </div>
  </NuxtLayout>
</template>