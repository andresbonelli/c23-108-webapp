/* eslint-disable react/prop-types */

import { GrSubtractCircle, GrAddCircle } from 'react-icons/gr';
import { motion, AnimatePresence } from 'framer-motion';

const Carrito = ({ cartItems, setCartItems }) => {
	const increaseQuantity = itemId => {
		setCartItems(prevItems =>
			prevItems.map(item =>
				item.id === itemId
					? { ...item, quantity: (item.quantity || 1) + 1 }
					: item
			)
		);
	};
	const decreaseQuantity = itemId => {
		setCartItems(prevItems => {
			const item = prevItems.find(item => item.id === itemId);
			if (item.quantity <= 1) {
				return prevItems.filter(item => item.id !== itemId);
			}
			return prevItems.map(item =>
				item.id === itemId ? { ...item, quantity: item.quantity - 1 } : item
			);
		});
	};

	const total = cartItems.reduce((sum, item) => {
		console.log('Item:', item.nombre);
		console.log('Precio:', item.precio);
		console.log('Quantity:', item.quantity);
		console.log('Subtotal:', item.precio * item.quantity);
		const itemTotal = item.precio * item.quantity;
		return sum + itemTotal;
	}, 0);

	return (
		<div
			style={{ backgroundImage: "url('/src/assets/img/bg_food.jpg')" }}
			className="container mx-auto p-8 bg-fixed min-h-screen"
		>
			<h2 className="text-6xl font-bold text-center mb-8 text-emerald-950  py-4 font-handwritten">
				Carrito de Compras
			</h2>
			<motion.div
				initial={{ opacity: 0 }}
				animate={{ opacity: 1 }}
				transition={{ duration: 0.5 }}
				className="grid gap-6"
			>
				<AnimatePresence>
					{cartItems.map(item => (
						<motion.div
							key={item.id}
							initial={{ opacity: 0, y: -20 }}
							animate={{ opacity: 1, y: 0 }}
							exit={{ opacity: 0, y: -20 }}
							transition={{ duration: 0.3 }}
							className="border bg-slate-50 rounded-lg p-4 shadow-md hover:shadow-lg"
						>
							<div className="flex items-center gap-4">
								<div className="w-24 h-24">
									<img
										src={item.img}
										alt={item.nombre}
										className="w-full h-full object-cover rounded-lg"
									/>
								</div>
								<div className="flex-1">
									<h3 className="text-xl font-semibold">{item.nombre}</h3>
									<p className="text-gray-600">{item.descripcion}</p>

									<div className="flex items-center gap-2">
										<button onClick={() => decreaseQuantity(item.id)}>
											<GrSubtractCircle className="text-red-600" />
										</button>
										<span className="text-lg font-medium w-8 text-center">
											{item.quantity || 1}
										</span>
										<button onClick={() => increaseQuantity(item.id)}>
											<GrAddCircle className="text-green-600" />
										</button>
									</div>
								</div>
								<p className="text-lg font-bold text-green-600">
									Precio: ${(item.precio * (item.quantity || 1)).toFixed(2)}
								</p>
							</div>
						</motion.div>
					))}
				</AnimatePresence>

				{cartItems.length === 0 ? (
					<p className="text-center text-emerald-600 bg-white p-4 rounded-lg">
						No hay productos en el carrito
					</p>
				) : (
					<div className="mt-6 bg-slate-50 w-fit ml-auto p-4 rounded-lg shadow-md">
						<p className="text-2xl font-bold text-red-600 text-right">
							Total: ${total.toFixed(2)}
						</p>
					</div>
				)}
			</motion.div>
		</div>
	);
};

export default Carrito;
