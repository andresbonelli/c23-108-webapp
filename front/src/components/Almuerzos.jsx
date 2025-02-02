/* eslint-disable react/prop-types */
import { TiShoppingCart } from 'react-icons/ti';
import { motion } from 'framer-motion';
const almuerzos = [
	{
		id: 1,
		nombre: 'Arroz con Pollo',
		precio: 12.5,
		descripcion: 'Arroz salteado con pollo y vegetales',
		imagen:
			'https://images.unsplash.com/photo-1603133872878-684f208fb84b?q=80&w=300',
	},
	{
		id: 2,
		nombre: 'Pasta Carbonara',
		precio: 11.5,
		descripcion: 'Pasta con salsa cremosa y tocino',
		imagen:
			'https://images.unsplash.com/photo-1612874742237-6526221588e3?q=80&w=300',
	},
	{
		id: 3,
		nombre: 'Ensalada César',
		precio: 9.0,
		descripcion: 'Lechuga romana, crutones, pollo y aderezo césar',
		imagen:
			'https://images.unsplash.com/photo-1550304943-4f24f54ddde9?q=80&w=300',
	},
	{
		id: 4,
		nombre: 'Salmón a la Parrilla',
		precio: 15.0,
		descripcion: 'Salmón fresco con vegetales asados',
		imagen:
			'https://images.unsplash.com/photo-1467003909585-2f8a72700288?q=80&w=300',
	},
	{
		id: 5,
		nombre: 'Bistec con Papas',
		precio: 14.0,
		descripcion: 'Bistec a la parrilla con papas fritas',
		imagen:
			'https://images.unsplash.com/photo-1600891964092-4316c288032e?q=80&w=300',
	},
];

const Almuerzos = ({ addToCart }) => {
	const handleAddToCart = product => {
		console.log(product);
		addToCart({
			id: product.id,
			img: product.imagen,
			nombre: product.nombre,
			precio: product.precio,
		});
	};
	return (
		<motion.div
			initial={{ opacity: 0 }}
			animate={{ opacity: 1 }}
			exit={{ opacity: 0 }}
			transition={{ duration: 1 }}
			style={{ backgroundImage: "url('/src/assets/img/bg_food.jpg')" }}
			className="container mx-auto p-8 bg-fixed"
		>
			<h2 className="text-8xl text-emerald-950 text-center font-bold mb-12 font-handwritten drop-shadow-lg">
				Menú de Almuerzos
			</h2>
			<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10 max-w-[90%] mx-auto">
				{almuerzos.map(almuerzo => (
					<div
						key={almuerzo.id}
						className="bg-white/90 backdrop-blur-sm rounded-2xl p-6 shadow-xl hover:shadow-2xl transition-all duration-300 hover:-translate-y-1 border-2 border-emerald-400"
					>
						<div className="relative mb-6">
							<img
								src={almuerzo.imagen}
								alt={almuerzo.nombre}
								className="w-full h-56 object-cover rounded-xl shadow-md hover:shadow-lg transition-shadow"
							/>
							<div className="absolute top-3 right-3 bg-amber-500 text-white px-4 py-1 rounded-full font-semibold shadow-lg">
								${almuerzo.precio.toFixed(2)}
							</div>
						</div>
						<h3 className="text-2xl font-bold text-emerald-950 mb-2">
							{almuerzo.nombre}
						</h3>
						<p className="text-emerald-700 mb-4 font-medium">
							{almuerzo.descripcion}
						</p>
						<button
							className="w-full bg-gradient-to-r from-emerald-500 to-emerald-600 text-white flex items-center justify-center px-6 py-4 rounded-xl hover:from-emerald-600 hover:to-emerald-700 transition-all duration-300 shadow-md hover:shadow-lg gap-3 font-semibold"
							onClick={() => handleAddToCart(almuerzo)}
						>
							<TiShoppingCart className="text-2xl" />
							<p>Agregar al Carrito</p>
						</button>
					</div>
				))}
			</div>
		</motion.div>
	);
};

export default Almuerzos;
