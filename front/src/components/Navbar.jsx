/* eslint-disable react/prop-types */
import { memo, useState } from 'react';
import { FaShoppingCart } from 'react-icons/fa';
import { HiMenu, HiX } from 'react-icons/hi';
import { Link } from 'react-router';

const Navbar = memo(({ cartItems }) => {
	const [isMenuOpen, setIsMenuOpen] = useState(false);
	const totalItems = cartItems.reduce((sum, item) => sum + item.quantity, 0);

	return (
		<nav className="bg-emerald-800 w-full py-4 px-4 md:px-8">
			<div className="container mx-auto flex justify-between items-center">
				{/* Mobile Menu Button */}
				<button
					className="lg:hidden text-white text-2xl"
					onClick={() => setIsMenuOpen(!isMenuOpen)}
				>
					{isMenuOpen ? <HiX /> : <HiMenu />}
				</button>

				{/* Desktop Navigation */}
				<div className="hidden lg:flex items-center space-x-6">
					<Link
						to="/"
						className="text-white text-lg font-semibold hover:text-emerald-200"
					>
						Inicio
					</Link>
					<Link
						to="/almuerzos"
						className="text-white text-lg hover:text-emerald-200"
					>
						Almuerzos
					</Link>
					<Link
						to="/bebidas"
						className="text-white text-lg hover:text-emerald-200"
					>
						Bebidas
					</Link>
					<Link
						to="/snacks"
						className="text-white text-lg hover:text-emerald-200"
					>
						Snacks
					</Link>
				</div>

				{/* Shopping Cart Icon */}
				<Link
					to="/carrito"
					className="text-white relative text-2xl hover:text-emerald-200"
				>
					{totalItems > 0 && (
						<span className="absolute -top-2 -right-2 bg-yellow-500 text-xs text-emerald-900 font-bold rounded-full h-5 w-5 flex items-center justify-center">
							{totalItems}
						</span>
					)}
					<FaShoppingCart />
				</Link>
			</div>

			{/* Mobile Menu */}
			<div
				className={`lg:hidden absolute left-0 right-0 bg-emerald-800 px-4 py-2 ${
					isMenuOpen ? 'block' : 'hidden'
				}`}
			>
				<div className="flex flex-col space-y-3">
					<Link
						to="/"
						className="text-white text-lg font-semibold hover:text-emerald-200"
						onClick={() => setIsMenuOpen(false)}
					>
						Inicio
					</Link>
					<Link
						to="/almuerzos"
						className="text-white text-lg hover:text-emerald-200"
						onClick={() => setIsMenuOpen(false)}
					>
						Almuerzos
					</Link>
					<Link
						to="/bebidas"
						className="text-white text-lg hover:text-emerald-200"
						onClick={() => setIsMenuOpen(false)}
					>
						Bebidas
					</Link>
					<Link
						to="/snacks"
						className="text-white text-lg hover:text-emerald-200"
						onClick={() => setIsMenuOpen(false)}
					>
						Snacks
					</Link>
				</div>
			</div>
		</nav>
	);
});

Navbar.displayName = 'Navbar';

export default Navbar;
