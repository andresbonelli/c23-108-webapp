/* eslint-disable react/prop-types */
import { FaShoppingCart } from 'react-icons/fa';
import { Link } from 'react-router';

const Navbar = ({ cartItems }) => {
	const cartItemCount = cartItems.reduce(
		(total, item) => total + (item.quantity || 1),
		0
	);
	return (
		<nav className="bg-emerald-800 py-4 px-8">
			<div className="container mx-auto flex justify-between items-center">
				<div className="flex items-center space-x-6">
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
				<Link
					to="/carrito"
					className="text-white relative text-2xl hover:text-emerald-200"
				>
					{cartItemCount > 0 && (
						<span className="absolute -top-2 -right-2 bg-yellow-500 text-xs text-emerald-900 font-bold rounded-full h-5 w-5 flex items-center justify-center">
							{cartItemCount}
						</span>
					)}
					<FaShoppingCart />
				</Link>
			</div>
		</nav>
	);
};

export default Navbar;
