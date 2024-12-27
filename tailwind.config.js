/** @type {import('tailwindcss').Config} */
module.exports = {
  safelist: [
    'text-red-600', // Preserve this class
  ],
  content: ["src/main/resources/templates/*.html"], //thymeleaf templates
  theme: {
    extend: {},
  },
  plugins: [
    require('daisyui'),
  ],
}

