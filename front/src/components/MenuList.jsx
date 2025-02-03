/* eslint-disable react/prop-types */
import { TiShoppingCart } from 'react-icons/ti';
import { motion } from 'framer-motion';
import { useState, useEffect } from 'react';

const MenuList = ({ addToCart, category }) => {
	const [menus, setMenus] = useState([]);
	useEffect(() => {
		fetch(`http://localhost:8080/api/menu/category/${category}`)
			.then(response => response.json())
			.then(data => {
				setMenus(data);
			})
			.catch(error => {
				console.error('Error fetching menus:', error);
			});
	}, [category]);
	const handleAddToCart = menu => {
		console.log(menu);
		addToCart({
			id: menu.id,
			img: menu.image,
			name: menu.name,
			price: menu.price,
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
				Menú de {category}
			</h2>
			<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10 max-w-[90%] mx-auto">
				{menus.map(menu => (
					<div
						key={menu.id}
						className="bg-white/90 backdrop-blur-sm rounded-2xl p-6 shadow-xl hover:shadow-2xl transition-all duration-300 hover:-translate-y-1 border-2 border-emerald-400"
					>
						<div className="relative mb-6">
							<img
								src={menu.image}
								alt={menu.name}
								className="w-full h-56 object-cover rounded-xl shadow-md hover:shadow-lg transition-shadow"
							/>
							<div className="absolute top-3 right-3 bg-amber-500 text-white px-4 py-1 rounded-full font-semibold shadow-lg">
								${menu.price.toFixed(2)}
							</div>
						</div>
						<h3 className="text-2xl font-bold text-emerald-950 mb-2">
							{menu.name}
						</h3>
						<p className="text-emerald-700 mb-4 font-medium">
							{menu.description}
						</p>
						<button
							className="w-full bg-gradient-to-r from-emerald-500 to-emerald-600 text-white flex items-center justify-center px-6 py-4 rounded-xl hover:from-emerald-600 hover:to-emerald-700 transition-all duration-300 shadow-md hover:shadow-lg gap-3 font-semibold"
							onClick={() => handleAddToCart(menu)}
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

export default MenuList;
