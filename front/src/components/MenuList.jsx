/* eslint-disable react/prop-types */
import { TiShoppingCart } from 'react-icons/ti';
import { motion } from 'framer-motion';
import { useState, useEffect, useCallback, memo } from 'react';

// 1. Memoizamos todo el componente para evitar re-renders innecesarios
const MenuList = memo(({ addToCart, category }) => {
	const [menus, setMenus] = useState([]);

	// 2. Manejamos el estado de carga y error
	const [isLoading, setIsLoading] = useState(true);
	const [error, setError] = useState(null);

	// 3. Memoizamos el fetch de datos
	useEffect(() => {
		const fetchData = async () => {
			try {
				setIsLoading(true);
				const response = await fetch(
					`http://localhost:8080/api/menu/category/${category}`
				);
				if (!response.ok) throw new Error('Error en la respuesta');
				const data = await response.json();
				setMenus(data);
			} catch (err) {
				console.error('Error fetching menus:', err);
				setError(err.message);
			} finally {
				setIsLoading(false);
			}
		};

		fetchData();
	}, [category]); // Solo se ejecuta cuando cambia la categoría

	// 4. Memoizamos el manejador del carrito
	const handleAddToCart = useCallback(
		(e, menu) => {
			e.preventDefault();
			e.stopPropagation();

			addToCart({
				id: menu.id,
				img: menu.image,
				name: menu.name,
				price: menu.price,
			});
		},
		[addToCart]
	); // Dependencia estable

	// 5. Optimizamos el renderizado de items
	const renderMenuItems = useCallback(() => {
		if (isLoading)
			return <div className="text-center text-2xl">Cargando...</div>;
		if (error)
			return <div className="text-center text-red-500">Error: {error}</div>;

		return menus.map(menu => (
			<div
				key={menu.id} // Evitamos usar index en el key
				className="bg-white/90 backdrop-blur-sm rounded-2xl p-6 shadow-xl hover:shadow-2xl transition-all duration-300 hover:-translate-y-1 border-2 border-emerald-400"
			>
				<div className="relative mb-6">
					<img
						src={menu.image}
						alt={menu.name}
						loading="lazy" // 6. Lazy loading para imágenes
						className="w-full h-56 object-cover rounded-xl shadow-md hover:shadow-lg transition-shadow"
					/>
					<div className="absolute top-3 right-3 bg-amber-500 text-white px-4 py-1 rounded-full font-semibold shadow-lg">
						${menu.price.toFixed(2)}
					</div>
				</div>
				<h3 className="text-2xl font-bold text-emerald-950 mb-2">
					{menu.name}
				</h3>
				<p className="text-emerald-700 mb-4 font-medium">{menu.description}</p>
				<button
					onPointerDown={e => e.preventDefault()} // Mejor manejo de eventos
					onClick={e => handleAddToCart(e, menu)}
					className="w-full bg-gradient-to-r from-emerald-500 to-emerald-600 text-white flex items-center justify-center px-6 py-4 rounded-xl hover:from-emerald-600 hover:to-emerald-700 transition-all duration-300 shadow-md hover:shadow-lg gap-3 font-semibold focus:outline-none"
				>
					<TiShoppingCart className="text-2xl" />
					<p>Agregar al Carrito</p>
				</button>
			</div>
		));
	}, [menus, isLoading, error, handleAddToCart]);

	return (
		<motion.div
			initial={{ opacity: 0 }}
			animate={{ opacity: 1 }}
			transition={{ duration: 1 }}
			style={{ backgroundImage: "url('/src/assets/img/bg_food.jpg')" }}
			className="container mx-auto p-8 bg-fixed"
		>
			<h2 className="text-8xl text-emerald-950 text-center font-bold mb-12 font-handwritten drop-shadow-lg">
				Menú de {category}
			</h2>
			<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10 max-w-[90%] mx-auto">
				{renderMenuItems()}
			</div>
		</motion.div>
	);
});

// 7. Aseguramos la visualización del nombre del componente en DevTools
MenuList.displayName = 'MenuList';

export default MenuList;
