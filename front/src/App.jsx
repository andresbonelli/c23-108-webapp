import { Routes, Route } from 'react-router';
import Home from './components/Home';
import MenuList from './components/MenuList';
import Carrito from './components/Carrito';
import { useState, useCallback } from 'react';
import Navbar from './components/Navbar';
import { AnimatePresence } from 'framer-motion';

function App() {
	const [cartItems, setCartItems] = useState([]);

	const addToCart = useCallback(
		menuItem => {
			setCartItems(prevItems => {
				const existingItem = prevItems.find(item => item.id === menuItem.id);
				if (existingItem) {
					return prevItems.map(item =>
						item.id === menuItem.id
							? { ...item, quantity: item.quantity + 1 }
							: item
					);
				}
				return [...prevItems, { ...menuItem, quantity: 1 }];
			});
		},
		[setCartItems]
	);

	return (
		<>
			<Navbar cartItems={cartItems} />
			<AnimatePresence mode="wait">
				<Routes location={location} key={location.pathname}>
					<Route path="/*" element={<h1>not found</h1>} />
					<Route path="/" element={<Home />} />
					<Route
						path="/almuerzos"
						element={<MenuList addToCart={addToCart} category={'almuerzos'} />}
					/>
					<Route
						path="/bebidas"
						element={<MenuList addToCart={addToCart} category={'bebidas'} />}
					/>
					<Route
						path="/snacks"
						element={<MenuList addToCart={addToCart} category={'snacks'} />}
					/>
					<Route
						path="/carrito"
						element={
							<Carrito cartItems={cartItems} setCartItems={setCartItems} />
						}
					/>
				</Routes>
			</AnimatePresence>
		</>
	);
}

export default App;
