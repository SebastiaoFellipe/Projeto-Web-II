import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import FuncionarioPage from './pages/FuncionarioPage';
import ProfessorPage from './pages/ProfessorPage';
import PalestraPage from './pages/PalestraPage';
import VisitaPage from './pages/VisitaPage';
import AnimalPage from './pages/AnimalPage';
import HabitatPage from './pages/HabitatPage';
import CandidatoPage from './pages/CandidatoPage';

function DashboardHome() {
  return <h1 className="text-4xl font-bold">Bem-vindo ao Dashboard</h1>;
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<DashboardHome />} />
        <Route path="funcionarios" element={<FuncionarioPage />} />
        <Route path="professores" element={<ProfessorPage />} />
        <Route path="palestras" element={<PalestraPage />} />
        <Route path="visitas" element={<VisitaPage />} />
        <Route path="animais" element={<AnimalPage />} />
        <Route path="habitats" element={<HabitatPage />} />
        <Route path="candidatos" element={<CandidatoPage />} />
      </Route>
    </Routes>
  );
}

export default App;