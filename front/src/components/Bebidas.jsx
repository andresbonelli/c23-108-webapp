const Bebidas = () => {
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
		<div
			style={{ backgroundImage: "url('/src/assets/img/bg_food.jpg')" }}
			className="container mx-auto p-8 bg-fixed"
		>
			<h2 className="text-7xl text-emerald-800 text-center font-bold mb-8 font-handwritten drop-shadow-lg">
				Bebidas Disponibles
			</h2>
			<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 max-w-[90%] mx-auto">
				{bebidas.map(bebida => (
					<div
						key={bebida.id}
						className="bg-white/90 backdrop-blur-sm border-2 border-emerald-200 rounded-xl p-6 shadow-lg hover:shadow-xl hover:scale-105 transition-all duration-300"
					>
						<div className="relative mb-4">
							<img
								src={bebida.imagen}
								alt={bebida.nombre}
								className="w-full h-48 object-cover rounded-lg shadow-md"
							/>
							<div className="absolute top-2 right-2 bg-emerald-500 text-white px-3 py-1 rounded-full font-bold">
								${bebida.precio.toFixed(2)}
							</div>
						</div>
						<h3 className="text-2xl font-handwritten text-emerald-900 mb-2">{bebida.nombre}</h3>
						<p className="text-emerald-700 mb-4 italic">{bebida.descripcion}</p>
						<button className="w-full bg-gradient-to-r from-emerald-500 to-emerald-600 text-white font-bold py-3 px-6 rounded-lg hover:from-emerald-600 hover:to-emerald-700 transition-all duration-300 shadow-md hover:shadow-lg transform active:scale-95">
							Agregar al Carrito
						</button>
					</div>
				))}
			</div>
		</div>
	);
};

export default Bebidas;
