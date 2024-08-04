<script lang="ts" setup>
const props = withDefaults(defineProps<{
  duration?: number,
  enabled?: boolean
  selectionBar?: boolean,
  size: number,
}>(), {
  duration: 10000,
  enabled: true
})

const container = ref<HTMLDivElement | null>(null)
const selected = ref(0)
let timerId: NodeJS.Timeout | null = null

const select = (i: number) => {
  selected.value = i
  resetTimer()
}

const startTimer = () => {
  if (!props.enabled) return

  timerId = setInterval(() => {
    selected.value = (selected.value + 1) % props.size
  }, props.duration)
}

const resetTimer = () => {
  stopTimer()
  startTimer()
}

const stopTimer = () => {
  if (timerId) {
    clearInterval(timerId)
  }
}

onMounted(startTimer)
onUnmounted(stopTimer)

watch(() => props.enabled, (enabled) => {
  if (enabled) {
    resetTimer()
  } else {
    stopTimer()
  }
})
</script>

<template>
  <div>
    <div ref="container" class="relative h-full">
      <slot :selected="selected"/>
    </div>
    <div v-if="selectionBar" class="flex justify-center space-x-4 mt-4">
      <div v-for="(n, i) in size"
           :aria-label="`Select element ${n}`"
           :class="[i === selected ? 'bg-white scale-125' : 'bg-neutral-400']"
           class="size-2"
           role="button"
           @click="select(i)"/>
    </div>
  </div>
</template>