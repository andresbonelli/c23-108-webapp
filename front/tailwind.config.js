/** @type {import('tailwindcss').Config} */
export default {
	content: ['./src/**/*.{js,jsx,ts,tsx}'],
	theme: {
		extend: {
			fontFamily: {
				handwritten: ['"Just Another Hand"', 'cursive'], // Define la nueva fuente
			},
		},
	},
	plugins: [],
};
