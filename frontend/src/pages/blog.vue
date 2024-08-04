<script lang="ts" setup>
import PurpleCraftButton from "~/components/buttons/craft/PurpleCraftButton.vue";
import ArticleCardBig from "~/components/blog/ArticleCardBig.vue";
import Header from "~/components/layout/Header.vue";
import Content from "~/components/layout/Content.vue";

useHead({
  title: "Blog"
})
</script>

<template>
  <NuxtLayout>
    <div class="w-full">
      <Header>
        <div class="flex flex-col items-center text-center py-12">
          <p class="text-3xl font-minecraft-ten text-balance">Wanna be more up to date?</p>
          <p class="text-xl mb-8">Or just hangout with the community?</p>
          <PurpleCraftButton to="https://dc.roxymc.net/">Join our Discord server!</PurpleCraftButton>
        </div>
      </Header>
      <Content>
        <div id="blog">
          <TitledLine>Blog</TitledLine>
          <div class="flex px-2 gap-4 flex-wrap">
            <ArticleCardBig/>
            <ArticleCardBig/>
            <ArticleCardBig/>
          </div>
        </div>
      </Content>
    </div>
  </NuxtLayout>
</template>