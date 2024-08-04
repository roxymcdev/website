<script lang="ts" setup>
import Content from "~/components/layout/Content.vue";
import {PauseIcon, PlayIcon} from '@heroicons/vue/24/outline'
import {useLazyAsyncData} from "#app";
import {$fetch} from "ofetch";
import type {PlayerStats} from "~/player/PlayerStats";
import SpinningWheel from "~/components/SpinningWheel.vue";
import {IdleAnimation, SkinViewer} from "skinview3d";

const apiBase = useRuntimeConfig().public.apiBase
const player = useRoute().params.player

const {data: playerStats} = await useLazyAsyncData(`player_stats_${player}`, () => $fetch(`${apiBase}/player-stats/${player}`), {
  server: false,
  transform: (data) => data as PlayerStats
})

watch(playerStats, (stats) => {
  if (!stats) return

  useHead({
    title: `${stats.name}'s Stats`,
  });
});

const {data: profileInfo} = await useLazyAsyncData(`profile_info_${player}`, () => $fetch(`https://api.minetools.eu/profile/${player}`), {
  server: false
})

const skinArea = ref<HTMLCanvasElement | null>(null)
const paused = ref<boolean>(false)
let skinViewer: SkinViewer | null = null

watch(() => skinArea.value && profileInfo.value, () => {
  skinViewer = new SkinViewer({
    canvas: skinArea.value || undefined,
    width: skinArea.value?.offsetWidth,
    height: skinArea.value?.offsetHeight,
    fov: 70,
    zoom: 0.8,
    animation: new IdleAnimation(),
    skin: profileInfo.value.decoded.textures.SKIN.url,
    model: profileInfo.value.decoded.textures.SKIN.metadata?.model,
    cape: profileInfo.value.decoded.textures.CAPE?.url
  });

  skinViewer.autoRotate = !paused.value
  skinViewer.autoRotateSpeed = 0.5
  skinViewer.controls.enableZoom = false
})

const updateSkinViewerSize = () => {
  if (!skinViewer || !skinArea.value) return

  skinViewer.width = skinArea.value.offsetWidth
  skinViewer.height = skinArea.value.offsetHeight
}

onMounted(() => {
  window.addEventListener('resize', updateSkinViewerSize);
})

onUnmounted(() => {
  window.removeEventListener('resize', updateSkinViewerSize);
})

const togglePaused = () => {
  paused.value = !paused.value

  if (!skinViewer) return
  skinViewer.autoRotate = !paused.value
}

const getLanguage = (code: string) => {
  const lang = new Intl.DisplayNames(['en'], {type: 'language'});
  return lang.of(code);
}

const sumValues = (record?: Record<any, number>) => {
  return Object.values(record || {}).reduce((x, y) => x + y, 0)
}
</script>

<template>
  <NuxtLayout>
    <div class="w-full">
      <Content>
        <div class="px-2">
          <ClientOnly>
            <div v-if="playerStats" class="flex flex-col md:flex-row justify-center gap-x-12 gap-y-6">
              <div class="relative h-96 w-full md:w-72 bg-neutral-900 border-4 border-craft-outline">
                <canvas ref="skinArea" class="!h-full !w-full"/>
                <button class="absolute bottom-0 left-0 p-2 text-zinc-300" @click="togglePaused">
                  <PlayIcon v-if="paused" aria-hidden="true" class="block h-6 w-6"/>
                  <PauseIcon v-else aria-hidden="true" class="block h-6 w-6"/>
                </button>
              </div>
              <div class="flex flex-col xl:flex-row gap-x-12 gap-y-6">
                <div class="flex flex-col w-full md:w-[25rem]">
                  <TitledLine>General</TitledLine>
                  <div class="flex flex-col gap-4 px-2">
                    <div class="px-6 sm:px-8 py-4 bg-neutral-900 border-4 border-craft-outline">
                      <div class="grid grid-cols-2 items-center">
                        <p class="font-minecraft-five">Name:</p>
                        <p class="font-minecraft-seven text-xl mb-1 leading-3">
                          {{ playerStats.name }}
                        </p>
                        <p class="font-minecraft-five">Rank:</p>
                        <GroupTag :group="playerStats.group"/>
                        <p class="font-minecraft-five">Balance:</p>
                        <div class="flex items-center gap-1">
                          <img alt="" class="image-pixelated h-4" src="/images/fazcoin.png"/>
                          <p class="font-minecraft-seven text-xl mb-1 leading-3">
                            {{ playerStats.stats?.general?.balance || 0 }}
                          </p>
                        </div>
                        <p class="font-minecraft-five">Language:</p>
                        <p class="font-minecraft-seven text-xl mb-1 leading-3">
                          {{ getLanguage(playerStats.stats?.general?.language || 'en') }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="flex flex-col w-full md:w-[25rem]">
                  <TitledLine>Game Modes</TitledLine>
                  <div class="flex flex-col px-2">
                    <div class="px-6 sm:px-8 py-4 bg-neutral-900 border-4 border-craft-outline">
                      <p class="font-minecraft-ten text-2xl">Five Nights at Freddy's</p>
                      <div class="grid grid-cols-2 items-center">
                        <p class="font-minecraft-five">Exp:</p>
                        <p class="font-minecraft-seven text-xl mb-1 leading-3">
                          {{ playerStats.stats?.fnaf?.exp || 0 }}
                        </p>
                        <p class="font-minecraft-five">Played:</p>
                        <p class="font-minecraft-seven text-xl mb-1 leading-3">
                          {{ sumValues(playerStats.stats?.fnaf?.played) }}
                        </p>
                        <p class="font-minecraft-five">Wins:</p>
                        <p class="font-minecraft-seven text-xl mb-1 leading-3">
                          {{ sumValues(playerStats.stats?.fnaf?.wins) }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="flex justify-center">
              <SpinningWheel/>
            </div>
          </ClientOnly>
        </div>
      </Content>
    </div>
  </NuxtLayout>
</template>