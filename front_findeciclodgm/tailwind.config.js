module.exports = {
  content: [
    "./src/**/*.{js, jsx, ts, tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'principal': '#820933',
        'rosa': '#D84797',
        'gris-claro': '#F9F8F8',
        'gris': '#CDD3CE',
        'gris-oscuro': '#686963',
      },
      gridTemplateRows: {
        '[auto,auto,1fr]': 'auto auto 1fr',
      },
    },
  },
  plugins: [
    require('@tailwindcss/aspect-ratio'),
    require('@tailwindcss/forms'),
  ],
}