<script lang="ts" setup>
type TeamMember = {
  name: string,
  description: string,
  rank: ("owner" | "admin"),
  image: string,
  classes: string
}

const teamMembers: TeamMember[] = [
  {
    name: "Glicz",
    description: "Hi there, I’m Glicz! The Founder and Lead Developer of RoxyMC. I’m passionate about Minecraft and love bringing new ideas to life through coding. My goal is to create an engaging and seamless experience for everyone on the server. I’m constantly working to innovate and improve RoxyMC, ensuring it remains a vibrant community for all players.",
    rank: "owner",
    image: "glicz",
    classes: "bg-neutral-900"
  },
  {
    name: "Notro",
    description: "Hi, I’m Notro, the Co-Owner of RoxyMC. I’m dedicated to enhancing the player experience and supporting the community. My focus is on ensuring a welcoming and enjoyable environment for everyone on the server. I’m always here to help and make sure RoxyMC runs smoothly for all our players.",
    rank: "owner",
    image: "notro",
    classes: "bg-neutral-900"
  }
]
</script>

<template>
  <Carousel :size="teamMembers.length" selection-bar>
    <template #default="{ selected }">
      <div class="flex justify-center min-h-96 sm:h-96 w-full">
        <div class="max-w-[46rem] w-full">
          <div class="grid grid-rows-1 h-full bg-neutral-900 border-4 border-craft-outline">
            <div v-for="(teamMember, i) in teamMembers"
                 :class="[i === selected ? 'z-10 opacity-100' : 'z-0 invisible opacity-0']"
                 class="col-[1] row-[1] size-full transition-[visibility,opacity] ease-linear duration-75">
              <div class="flex h-full px-4 md:px-8 py-4 sm:py-8 gap-4 sm:gap-6 md:gap-8">
                <NuxtImg :alt="teamMember.name" :src="`/images/team/members/3d/${teamMember.image}.png`"
                         class="hidden sm:block min-w-48 max-w-48 object-contain" format="webp" preload quality="100"/>
                <div class="w-full">
                  <div class="flex sm:block flex-col items-center mb-2">
                    <NuxtImg :alt="teamMember.name" :src="`/images/team/members/2d/${teamMember.image}.png`"
                             class="sm:hidden w-20 image-pixelated" format="webp" preload quality="100"/>
                    <div class="flex items-center gap-2">
                      <GroupTag :group="teamMember.rank"/>
                      <p class="text-3xl font-minecraft-ten">{{ teamMember.name }}</p>
                    </div>
                  </div>
                  <p>{{ teamMember.description }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
  </Carousel>
</template>
