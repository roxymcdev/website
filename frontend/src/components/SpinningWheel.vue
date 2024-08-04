<template>
  <Carousel :duration="75" :size="8">
    <template #default="{ selected }">
      <div class="grid gap-1 grid-cols-3 grid-rows-3 select-none">
        <div v-for="i in [0, 1, 2, 7, null, 3, 6, 5, 4]"
             :class="i === null ? '' : (i === selected ? 'bg-white scale-125' : 'bg-neutral-400')"
             class="size-2"/>
      </div>
    </template>
  </Carousel>
</template>