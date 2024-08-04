import type {Config} from 'tailwindcss'

export default {
    content: [
        '/src/components/**/*.{vue,js,ts}',
        '/src/layouts/**/*.vue',
        '/src/pages/**/*.vue',
        '/src/composables/**/*.{js,ts}',
        '/src/plugins/**/*.{js,ts}',
        '/src/utils/**/*.{js,ts}',
        '/src/App.{js,ts,vue}',
        '/src/app.{js,ts,vue}',
        '/src/Error.{js,ts,vue}',
        '/src/error.{js,ts,vue}',
        '/src/app.config.{js,ts}'
    ],
    theme: {
        extend: {
            animation: {
                blink: 'blink 1s step-start 0s infinite'
            },
            colors: {
                'craft-outline': '#1E1E1F'
            },
            fontFamily: {
                sans: ['Inter'],
                'minecraft-five': ['MinecraftFive-Regular'],
                'minecraft-seven': ['MinecraftSeven'],
                'minecraft-ten': ['MinecraftTen'],
                unifont: ['Unifont']
            },
            keyframes: {
                blink: {
                    '50%': {opacity: '0'}
                }
            }
        }
    },
    plugins: [],
} satisfies Config

