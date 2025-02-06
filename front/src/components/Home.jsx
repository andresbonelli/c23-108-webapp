import food from '../assets/img/food.jpg';
import drink from '../assets/img/drink.jpg';
import snacks from '../assets/img/snacks.jpg';
import { NavLink } from 'react-router';
import { motion } from 'framer-motion';

const Home = () => {
	return (
		<motion.div
			initial={{ opacity: 0 }}
			animate={{ opacity: 1 }}
			exit={{ opacity: 0 }}
			transition={{ duration: 1 }}
			className="max-w-full"
		>
			<header className="min-h-[300px] lg:h-screen bg-[url('./assets/img/main_banner.jpg')]  bg-no-repeat bg-cover bg-center flex flex-col">
				<h1 className="font-handwritten text-7xl lg:text-[200px] m-auto text-white text-center">
					RoadBites
				</h1>
			</header>

			<main className="lg:h-screen flex flex-col w-full items-center justify-center p-11 bg-[url('./assets/img/bg_food.jpg')] bg-cover bg-center ">
				<h1 className="text-7xl text-emerald-950 text-center mb-10 font-handwritten">
					Nuestro menú
				</h1>
				<div className="w-full justify-center items-center space-y-4 lg:space-y-0 lg:space-x-8 flex flex-col lg:flex-row">
					<div className="flex flex-col-reverse w-full lg:w-1/3 rounded-2xl h-fit  overflow-hidden relative">
						<NavLink to="/almuerzos">
							<div className="absolute flex justify-center items-center h-20 bg-zinc-900 bg-opacity-70 bottom-0 w-full ">
								<p className="text-center text-5xl text-white font-handwritten">
									Almuerzos
								</p>
							</div>
							<img src={food} className="w-full object-cover" alt="" />
						</NavLink>
					</div>

					<div className="flex flex-col-reverse  w-full lg:w-1/3 rounded-2xl h-fit   overflow-hidden relative">
						<NavLink to="/bebidas">
							<div className="absolute bottom-0 flex justify-center items-center h-20 bg-zinc-900 bg-opacity-70 w-full ">
								<p className="text-center text-5xl text-white font-handwritten">
									Bebidas
								</p>
							</div>
							<img src={drink} className="w-full max-h-full" alt="" />
						</NavLink>
					</div>
					<div className="flex flex-col-reverse  w-full lg:w-1/3 rounded-2xl h-fit overflow-hidden relative">
						<NavLink to="/snacks">
							<div className="absolute bottom-0 flex justify-center items-center h-20 bg-zinc-900 bg-opacity-70 w-full ">
								<p className="text-center text-5xl text-white font-handwritten">
									Snacks
								</p>
							</div>
							<img src={snacks} className="w-full max-h-full" alt="" />
						</NavLink>
					</div>
				</div>
			</main>
		</motion.div>
	);
};

export default Home;
