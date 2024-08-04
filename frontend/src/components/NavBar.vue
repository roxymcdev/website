<script lang="ts" setup>
import {Disclosure, DisclosureButton, DisclosurePanel} from '@headlessui/vue'
import {Bars3Icon, XMarkIcon} from '@heroicons/vue/24/outline'
import PurpleButton from "~/components/buttons/craft/PurpleCraftButton.vue";
import NavButton from "~/components/buttons/nav/NavButton.vue";

withDefaults(defineProps<{
  position?: ("sticky" | "fixed")
}>(), {
  position: "sticky"
})

type Link = {
  name: string,
  to: string,
}

const navigation: Link[] = [
  {name: "Home", to: "/"},
  {name: "Blog", to: "/blog"},
  {name: "Player Stats", to: "/player-stats"}
]
</script>

<template>
  <Disclosure v-slot="{ open }" :class="position" as="nav" class="z-50 top-0 w-full">
    <div class="bg-neutral-900 border-b-4 border-x-4 border-craft-outline mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
      <div class="relative flex h-16 items-center justify-between">
        <div class="inset-y-0 left-0 flex items-center sm:collapse mr-2">
          <DisclosureButton
              class="relative inline-flex items-center justify-center p-2 text-zinc-300">
            <span class="absolute -inset-0.5"/>
            <span class="sr-only">Open main menu</span>
            <Bars3Icon v-if="!open" aria-hidden="true" class="block h-6 w-6"/>
            <XMarkIcon v-else aria-hidden="true" class="block h-6 w-6"/>
          </DisclosureButton>
        </div>

        <div class="flex flex-1 items-center">
          <div class="flex flex-shrink-0 items-center">
            <NuxtLink class="flex items-center" to="/">
              <img alt="" class="h-8 w-auto mr-2 image-pixelated" src="/favicon.ico"/>
              <p class="text-2xl font-minecraft-ten">RoxyMC</p>
            </NuxtLink>
          </div>

          <div class="hidden sm:ml-6 sm:flex gap-4 font-minecraft-five">
            <NavButton v-for="item in navigation" :key="item.name" :to="item.to">{{ item.name }}</NavButton>
          </div>

          <div class="hidden sm:ml-6 sm:mr-2 sm:flex gap-4 flex-1 justify-end">
            <PurpleButton class="-mb-1" to="https://dc.roxymc.net/">Discord</PurpleButton>
          </div>
        </div>
      </div>
    </div>

    <DisclosurePanel
        class="absolute w-full bg-neutral-900 border-b-4 border-x-4 border-craft-outline sm:hidden -translate-y-1">
      <div class="flex flex-col gap-3 px-2 pb-3">
        <div class="flex flex-col gap-2 px-1 font-minecraft-five">
          <NavButton v-for="item in navigation" :key="item.name" :to="item.to">{{ item.name }}</NavButton>
        </div>
        <div class="flex flex-col gap-4 mt-2">
          <PurpleButton to="https://dc.roxymc.net/">Discord</PurpleButton>
        </div>
      </div>
    </DisclosurePanel>
  </Disclosure>
</template>