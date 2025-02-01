import { NavLink } from 'react-router';

/* eslint-disable react/prop-types */
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
		<div
			style={{ backgroundImage: "url('/src/assets/img/bg_food.jpg')" }}
			className="container mx-auto p-8 bg-fixed"
		>
			<h2 className="text-7xl text-emerald-950 text-center font-bold mb-4 font-handwritten">
				Menú de Almuerzos
			</h2>
			<div className="grid  grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 max-w-[90%] mx-auto">
				{almuerzos.map(almuerzo => (
					<div
						key={almuerzo.id}
						className="border bg-slate-50 rounded-lg p-4 shadow-md hover:shadow-lg transition-shadow"
					>
						<img
							src={almuerzo.imagen}
							alt={almuerzo.nombre}
							className="w-full h-48 object-cover rounded-lg mb-4"
						/>
						<h3 className="text-xl font-semibold">{almuerzo.nombre}</h3>
						<p className="text-gray-600">{almuerzo.descripcion}</p>
						<p className="text-green-600 font-bold mt-2">
							${almuerzo.precio.toFixed(2)}
						</p>
						<button
							className="mt-2 bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 transition-colors w-full"
							onClick={() => handleAddToCart(almuerzo)} // Changed from productData to almuerzo
						>
							Agregar al Carrito
						</button>
					</div>
				))}
			</div>
		</div>
	);
};

export default Almuerzos;
