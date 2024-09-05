<script lang="ts" setup>
import TitledLine from "~/components/TitledLine.vue";
import ArticleCardSmall from "~/components/blog/ArticleCardSmall.vue";
import DarkCraftButton from "~/components/buttons/craft/DarkCraftButton.vue";
import GameModesSection from "~/components/main/GameModesSection.vue";
import MeetOurTeamSection from "~/components/main/MeetOurTeamSection.vue";
import Header from "~/components/layout/Header.vue";
import Content from "~/components/layout/Content.vue";
import ServerAddress from "~/components/main/ServerAddress.vue";

useHead({
  title: "Home"
})
</script>

<template>
  <NuxtLayout navbar-position="fixed">
    <div class="w-full">
      <Header>
        <div class="relative">
          <NuxtImg alt="" class="h-svh sm:h-[720px] object-cover select-none" format="webp" preload quality="100"
                   src="/images/background.jpg"/>
          <div class="absolute top-0 left-0 flex flex-col justify-center items-center w-full h-full">
            <p class="font-minecraft-ten text-7xl mb-8">RoxyMC</p>
            <p class="text-xl -mb-2">Join our server at</p>
            <ServerAddress/>
          </div>
        </div>
      </Header>
      <Content>
        <div id="news">
          <TitledLine>News</TitledLine>
          <div class="flex px-2 gap-x-4 overflow-x-scroll snap-mandatory snap-x sm:snap-normal">
            <ArticleCardSmall class="snap-center"/>
            <ArticleCardSmall class="snap-center"/>
            <ArticleCardSmall class="snap-center"/>
            <ArticleCardSmall class="snap-center"/>
          </div>
          <div class="flex justify-center mt-6">
            <DarkCraftButton to="/blog">See more</DarkCraftButton>
          </div>
        </div>
        <div id="game-modes">
          <TitledLine>Game Modes</TitledLine>
          <div class="px-2">
            <GameModesSection/>
          </div>
        </div>
        <div id="meet-out-team">
          <TitledLine>Meet Our Team</TitledLine>
          <div class="px-2">
            <MeetOurTeamSection/>
          </div>
        </div>
      </Content>
    </div>
  </NuxtLayout>
</template>