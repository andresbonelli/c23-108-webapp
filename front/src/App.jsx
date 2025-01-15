import Navbar from './components/Navbar';
import food from './assets/img/food.jpg';
import drink from './assets/img/drink.jpg';
import snacks from './assets/img/snacks.jpg';

function App() {
	return (
		<>
			<header className="h-screen bg-[url('./assets/img/main_banner.jpg')] bg-cover bg-center flex flex-col">
				<Navbar />
				<h1 className="font-handwritten text-[200px] m-auto text-white text-center">
					Food Truck
				</h1>
			</header>
			<main className="h-screen flex flex-col w-full items-center justify-center p-11 bg-[url('./assets/img/bg_food.jpg')] bg-cover bg-center ">
				<hi className="text-7xl text-emerald-950 text-center mb-10 font-handwritten">
					Nuestro menú
				</hi>
				<div className="w-full space-x-8 flex">
					<div className="flex flex-col-reverse w-1/3 rounded-2xl h-fit overflow-hidden relative">
						<img src={food} className="w-full" alt="" />
						<div className="absolute flex justify-center items-center h-20 bg-zinc-900 bg-opacity-70 w-full ">
							<p className="text-center text-5xl text-white font-handwritten">
								Almuerzos
							</p>
						</div>
					</div>
					<div className="flex flex-col-reverse w-1/3 rounded-2xl h-fit max-h-[349px]  overflow-hidden relative">
						<img src={drink} className="w-full" alt="" />
						<div className="absolute flex justify-center items-center h-20 bg-zinc-900 bg-opacity-70 w-full ">
							<p className="text-center text-5xl text-white font-handwritten">
								Bebidas
							</p>
						</div>
					</div>
					<div className="flex flex-col-reverse w-1/3 rounded-2xl h-fit max-h-[349px] overflow-hidden relative">
						<img src={snacks} className="w-full" alt="" />
						<div className="absolute flex justify-center items-center h-20 bg-zinc-900 bg-opacity-70 w-full ">
							<p className="text-center text-5xl text-white font-handwritten">
								Snacks
							</p>
						</div>
					</div>
				</div>
			</main>
		</>
	);
}

export default App;
