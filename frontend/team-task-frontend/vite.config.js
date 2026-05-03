import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// simple config no tailwind plugin needed
export default defineConfig({
  plugins: [react()],
})