/* eslint-disable react/prop-types */

import { GrSubtractCircle, GrAddCircle } from 'react-icons/gr';
import { motion, AnimatePresence } from 'framer-motion';
import { useState } from 'react';
import Loader from './Loader';

const Carrito = ({ cartItems, setCartItems }) => {
	const [nombre, setNombre] = useState('');
	const [email, setEmail] = useState('');
	const [isLoading, setIsLoading] = useState(false);

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
		const itemTotal = item.price * item.quantity;
		return sum + itemTotal;
	}, 0);

	const handleSubmit = e => {
		e.preventDefault();
		setIsLoading(true);
		let orderMenus = [];
		for (let i = 0; i < cartItems.length; i++) {
			orderMenus.push({
				menuId: cartItems[i].id,
				menuName: cartItems[i].name,
				image: cartItems[i].img,
				price: cartItems[i].price,
				quantity: cartItems[i].quantity,
			});
		}

		const orden = {
			userName: nombre,
			userEmail: email,
			orderMenus,
		};
		fetch('http://localhost:8080/api/order', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(orden),
		})
			.then(res => res.json())
			.then(response => {
				console.log(response);
				setIsLoading(false);
			});
	};

	return (
		<motion.div
			initial={{ opacity: 0 }}
			animate={{ opacity: 1 }}
			exit={{ opacity: 0 }}
			transition={{ duration: 1 }}
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
					{cartItems.map((item, index) => (
						<motion.div
							key={`${item.id}-${index}`}
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
										alt={item.name}
										className="w-full h-full object-cover rounded-lg"
									/>
								</div>
								<div className="flex-1">
									<h3 className="text-xl font-semibold">{item.name}</h3>
									<p className="text-gray-600">{item.description}</p>

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
									price: ${(item.price * (item.quantity || 1)).toFixed(2)}
								</p>
							</div>
						</motion.div>
					))}
				</AnimatePresence>

				{cartItems.length === 0 ? (
					<p className="text-center text-emerald-600 bg-white p-4 rounded-lg">
						No hay menuos en el carrito
					</p>
				) : (
					<div className="mt-6 bg-slate-50 w-fit ml-auto p-4 rounded-lg shadow-md">
						<p className="text-2xl font-bold text-red-600 text-right">
							Total: ${total.toFixed(2)}
						</p>
					</div>
				)}
				<form className="mt-2 bg-slate-50 p-6 rounded-lg shadow-md flex flex-col gap-4 w-1/2 mx-auto">
					<input
						type="text"
						onChange={e => setNombre(e.target.value)}
						placeholder="Nombre"
						className="p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-emerald-500"
					/>
					<input
						type="email"
						onChange={e => setEmail(e.target.value)}
						placeholder="Email"
						className="p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-emerald-500"
					/>
					{isLoading ? (
						<Loader />
					) : (
						<button
							onClick={handleSubmit}
							className="bg-emerald-600 text-white font-semibold py-2 px-4 rounded-lg hover:bg-emerald-700 transition-colors"
						>
							Ordenar
						</button>
					)}
				</form>
			</motion.div>
		</motion.div>
	);
};

export default Carrito;
