<script lang="ts" setup>
type GameMode = {
  title: string,
  description: string,
  images: string[],
  extra?: {
    classes?: (string | string[])
    content: string[]
  }
}

const gameModes: GameMode[] = [
  {
    title: "Five Nights at Freddy's",
    description: "Dive into the spine-chilling world of Five Nights at Freddy's on our Minecraft server! Survive five nights of relentless scares in our faithfully recreated Freddy Fazbear's Pizza. Monitor cameras, conserve power, and brace for jump scares as you navigate through dark corridors. Can you outsmart the animatronics and make it through the night? Experience the thrills in this Minecraft adaptation of the iconic horror series!",
    images: [
      "/images/fnaf/1.png",
      "/images/fnaf/2.png",
      "/images/fnaf/3.png",
      "/images/fnaf/4.png",
    ],
    extra: {
      classes: "z-0 absolute bottom-0 right-0 flex justify-between items-end pointer-events-none select-none h-1/2 w-full opacity-40 image-pixelated",
      content: [
        `<img alt="" class="object-cover h-2/5" src="/images/fnaf/stillhere.png"/>`,
        `<img alt="" class="object-cover h-full" src="/images/fnaf/drkns.png"/>`
      ]
    }
  }
]
</script>

<template>
  <Carousel :size="gameModes.length" selection-bar>
    <template #default="{ selected }">
      <div v-for="(gameMode, i) in gameModes"
           :class="[i === selected ? 'z-0 opacity-100 relative' : 'z-10 invisible opacity-0 absolute top-0 left-0']"
           class="flex flex-col sm:flex-row gap-6 flex-grow transition-all ease-linear duration-75">
        <div class="w-full relative border-4 border-craft-outline">
          <Carousel :duration="2500" :enabled="i === selected" :size="gameMode.images.length"
                    class="z-10 relative h-full">
            <template #default="{ selected }">
              <NuxtImg v-for="(image, i) in gameMode.images"
                       :class="[i === selected ? 'z-10 relative' : 'z-0 absolute top-0 left-0']" :src="image" alt=""
                       class="h-full object-cover" format="webp" height="480" preload quality="100" width="854"/>
            </template>
          </Carousel>
          <div class="z-0 absolute top-0 left-0 w-full h-full animate-pulse bg-neutral-500"/>
        </div>
        <div class="w-full relative">
          <div class="z-10 relative">
            <p class="text-3xl font-minecraft-ten mb-2">{{ gameMode.title }}</p>
            <p>{{ gameMode.description }}</p>
          </div>
          <div v-if="gameMode.extra" :class="gameMode.extra.classes" v-html="gameMode.extra.content.join('')"/>
        </div>
      </div>
    </template>
  </Carousel>
</template>