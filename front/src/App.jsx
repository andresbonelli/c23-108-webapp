import { Routes, Route } from 'react-router';
import Home from './components/Home';
import Bebidas from './components/Bebidas';
import Snacks from './components/Snacks';
import Almuerzos from './components/Almuerzos';
import Carrito from './components/Carrito';
import { useState } from 'react';

function App() {
	const [cartItems, setCartItems] = useState([]);

	const addToCart = item => {
		setCartItems(prevItems => {
			// Check if item already exists in cart
			const existingItemIndex = prevItems.findIndex(
				cartItem => cartItem.id === item.id
			);

			if (existingItemIndex !== -1) {
				// If item exists, increase its quantity
				return prevItems.map((cartItem, index) => {
					if (index === existingItemIndex) {
						return {
							...cartItem,
							quantity: (cartItem.quantity || 1) + 1,
						};
					}
					return cartItem;
				});
			}

			// If item doesn't exist, add it with quantity 1
			return [...prevItems, { ...item, quantity: 1 }];
		});
	};

	const removeFromCart = itemId => {
		setCartItems(prevItems => prevItems.filter(item => item.id !== itemId));
	};

	return (
		<>
			<Routes>
				<Route path="/*" element={<h1>not found</h1>} />
				<Route path="/" element={<Home />} />
				<Route
					path="/almuerzos"
					element={<Almuerzos addToCart={addToCart} />}
				/>
				<Route path="/bebidas" element={<Bebidas />} />
				<Route path="/snacks" element={<Snacks />} />
				<Route
					path="/carrito"
					element={
						<Carrito
							cartItems={cartItems}
							removeFromCart={removeFromCart}
							setCartItems={setCartItems}
						/>
					}
				/>
			</Routes>
		</>
	);
}

export default App;
