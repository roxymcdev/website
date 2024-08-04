// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    srcDir: 'src/',
    compatibilityDate: '2024-04-03',
    modules: ['@nuxtjs/tailwindcss', '@nuxt/fonts', "@nuxt/image"],
    runtimeConfig: {
        public: {
            apiBase: '/api'
        }
    }
})