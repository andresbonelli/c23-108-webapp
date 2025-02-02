/* eslint-disable react/prop-types */
import { TiShoppingCart } from 'react-icons/ti';
import { motion } from 'framer-motion';
const Bebidas = ({ addToCart }) => {
	const handleAddToCart = product => {
		addToCart({
			id: product.id,
			img: product.imagen,
			nombre: product.nombre,
			precio: product.precio,
		});
	};
	const bebidas = [
		{
			id: 1,
			nombre: 'Coca-Cola',
			precio: 2.5,
			descripcion: 'Refresco carbonatado',
			imagen:
				'https://images.unsplash.com/photo-1554866585-cd94860890b7?q=80&w=300',
		},
		{
			id: 2,
			nombre: 'Agua Mineral',
			precio: 1.5,
			descripcion: 'Agua con gas',
			imagen:
				'https://images.unsplash.com/photo-1616118132534-381148898bb4?q=80&w=300',
		},
		{
			id: 3,
			nombre: 'Jugo de Naranja',
			precio: 3.0,
			descripcion: 'Jugo natural',
			imagen:
				'https://images.unsplash.com/photo-1613478223719-2ab802602423?q=80&w=300',
		},
		{
			id: 4,
			nombre: 'Café Americano',
			precio: 2.0,
			descripcion: 'Café caliente',
			imagen:
				'https://images.unsplash.com/photo-1509042239860-f550ce710b93?q=80&w=300',
		},
		{
			id: 5,
			nombre: 'Limonada',
			precio: 4.0,
			descripcion: 'Limonada de lima',
			imagen:
				'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJG9LkODHmYWVx13R2i6VncSEQ6pgDMv8cQ&s',
		},
		{
			id: 5,
			nombre: 'Limonada',
			precio: 4.0,
			descripcion: 'Limonada de lima',
			imagen:
				'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJG9LkODHmYWVx13R2i6VncSEQ6pgDMv8cQ&s',
		},
		{
			id: 6,
			nombre: 'Coca-Cola',
			precio: 2.5,
			descripcion: 'Refresco carbonatado',
			imagen:
				'https://images.unsplash.com/photo-1554866585-cd94860890b7?q=80&w=300',
		},
		{
			id: 7,
			nombre: 'Agua Mineral',
			precio: 1.5,
			descripcion: 'Agua con gas',
			imagen:
				'https://images.unsplash.com/photo-1616118132534-381148898bb4?q=80&w=300',
		},
		{
			id: 8,
			nombre: 'Jugo de Naranja',
			precio: 3.0,
			descripcion: 'Jugo natural',
			imagen:
				'https://images.unsplash.com/photo-1613478223719-2ab802602423?q=80&w=300',
		},
		{
			id: 9,
			nombre: 'Café Americano',
			precio: 2.0,
			descripcion: 'Café caliente',
			imagen:
				'https://images.unsplash.com/photo-1509042239860-f550ce710b93?q=80&w=300',
		},
		{
			id: 10,
			nombre: 'Limonada',
			precio: 4.0,
			descripcion: 'Limonada de lima',
			imagen:
				'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJG9LkODHmYWVx13R2i6VncSEQ6pgDMv8cQ&s',
		},
		{
			id: 11,
			nombre: 'Limonada',
			precio: 4.0,
			descripcion: 'Limonada de lima',
			imagen:
				'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJG9LkODHmYWVx13R2i6VncSEQ6pgDMv8cQ&s',
		},
	];

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
				Bebidas Disponibles
			</h2>
			<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10 max-w-[90%] mx-auto">
				{bebidas.map(bebida => (
					<div
						key={bebida.id}
						className="bg-white/90 backdrop-blur-sm rounded-2xl p-6 shadow-xl hover:shadow-2xl transition-all duration-300 hover:-translate-y-1 border-2 border-emerald-400"
					>
						<div className="relative mb-6">
							<img
								src={bebida.imagen}
								alt={bebida.nombre}
								className="w-full h-56 object-cover rounded-xl shadow-md hover:shadow-lg transition-shadow"
							/>
							<div className="absolute top-3 right-3 bg-amber-500 text-white px-4 py-1 rounded-full font-semibold shadow-lg">
								${bebida.precio.toFixed(2)}
							</div>
						</div>
						<h3 className="text-2xl font-bold text-emerald-950 mb-2">
							{bebida.nombre}
						</h3>
						<p className="text-emerald-700 mb-4 font-medium">
							{bebida.descripcion}
						</p>
						<button
							className="w-full bg-gradient-to-r from-emerald-500 to-emerald-600 text-white flex items-center justify-center px-6 py-4 rounded-xl hover:from-emerald-600 hover:to-emerald-700 transition-all duration-300 shadow-md hover:shadow-lg gap-3 font-semibold"
							onClick={() => handleAddToCart(bebida)}
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

export default Bebidas;
