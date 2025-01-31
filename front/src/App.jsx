import { Routes, Route } from 'react-router';
import Home from './components/Home';
import Bebidas from './components/Bebidas';
import Snacks from './components/Snacks';
import Almuerzos from './components/Almuerzos';

function App() {
	return (
		<>
			<Routes>
				<Route path="/*" element={<h1>not found</h1>} />
				<Route path="/" element={<Home />} />
				<Route path="/almuerzos" element={<Almuerzos />} />
				<Route path="/bebidas" element={<Bebidas />} />
				<Route path="/snacks" element={<Snacks />} />
			</Routes>
		</>
	);
}

export default App;
