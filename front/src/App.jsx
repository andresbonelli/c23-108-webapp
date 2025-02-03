import { Routes, Route } from 'react-router';
import Home from './components/Home';
import MenuList from './components/MenuList';
import Carrito from './components/Carrito';
import { useState } from 'react';
import Navbar from './components/Navbar';
import { AnimatePresence } from 'framer-motion';

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
							cartItem.name,
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

			console.log('Adding new item:', item.name, 'Quantity: 1');
			return [...prevItems, { ...item, quantity: 1 }];
		});
	};

	return (
		<AnimatePresence mode="wait">
			<Navbar cartItems={cartItems} />
			<Routes>
				<Route path="/*" element={<h1>not found</h1>} />
				<Route path="/" element={<Home />} />
				<Route
					path="/almuerzos"
					element={<MenuList addToCart={addToCart} category={"almuerzos"}/>}
				/>
				<Route path="/bebidas" element={<MenuList addToCart={addToCart} category={"bebidas"} />} />
				<Route path="/snacks" element={<MenuList addToCart={addToCart} category={"snacks"} />} />
				<Route
					path="/carrito"
					element={
						<Carrito cartItems={cartItems} setCartItems={setCartItems} />
					}
				/>
			</Routes>
		</AnimatePresence>
	);
}

export default App;
