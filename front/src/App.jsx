import { Routes, Route } from 'react-router';
import Home from './components/Home';
import Bebidas from './components/Bebidas';
import Snacks from './components/Snacks';
import Almuerzos from './components/Almuerzos';
import Carrito from './components/Carrito';
import { useState } from 'react';
import Navbar from './components/Navbar';

function App() {
	const [cartItems, setCartItems] = useState([]);

	const addToCart = item => {
		setCartItems(prevItems => {
			const existingItemIndex = prevItems.findIndex(
				cartItem => cartItem.id === item.id
			);

			if (existingItemIndex !== -1) {
				return prevItems.map((cartItem, index) => {
					if (index === existingItemIndex) {
						const newQuantity = Number(cartItem.quantity || 1) + 1;
						console.log(
							'Updating quantity for:',
							cartItem.nombre,
							'New quantity:',
							newQuantity
						);
						return {
							...cartItem,
							quantity: newQuantity,
						};
					}
					return cartItem;
				});
			}

			console.log('Adding new item:', item.nombre, 'Quantity: 1');
			return [...prevItems, { ...item, quantity: 1 }];
		});
	};

	return (
		<>
			<Navbar cartItems={cartItems} />
			<Routes>
				<Route path="/*" element={<h1>not found</h1>} />
				<Route path="/" element={<Home />} />
				<Route
					path="/almuerzos"
					element={<Almuerzos addToCart={addToCart} />}
				/>
				<Route path="/bebidas" element={<Bebidas addToCart={addToCart} />} />
				<Route path="/snacks" element={<Snacks addToCart={addToCart} />} />
				<Route
					path="/carrito"
					element={
						<Carrito cartItems={cartItems} setCartItems={setCartItems} />
					}
				/>
			</Routes>
		</>
	);
}

export default App;
