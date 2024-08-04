<template>
  <div class="relative w-[576px]">
    <NuxtImg alt="" class="w-[576px] object-contain image-pixelated select-none" format="webp" preload quality="100"
             src="/images/server_address_background.png"/>
    <div class="absolute top-0 left-0 flex justify-center w-full h-full">
      <div class="flex justify-center items-center w-lvw py-12 px-6">
        <div class="max-w-[450px] w-full font-minecraft-seven text-xl">
          <p class="text-[#aaaaaa] [text-shadow:2px_2px_#2a2a2a] text-sg mx-[1px]">Server Address</p>
          <div class="bg-black border-2 border-white flex justify-between pt-1 pb-2 px-2">
            <span class="[text-shadow:2px_2px_#3f3f3f]">
              roxymc.net<span class="animate-blink select-none">_</span>
            </span>
            <span class="text-[#aaaaaa] [text-shadow:2px_2px_#2a2a2a] font-unifont select-none"
                  onclick="navigator.clipboard.writeText('roxymc.net')" role="button">❐</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>