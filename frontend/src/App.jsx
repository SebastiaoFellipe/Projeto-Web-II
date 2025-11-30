import { Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";

import FuncionarioPage from "./pages/FuncionarioPage";
import ProfessorPage from "./pages/ProfessorPage";
import PalestraPage from "./pages/PalestraPage";
import VisitaPage from "./pages/VisitaPage";
import AnimalPage from "./pages/AnimalPage";
import HabitatPage from "./pages/HabitatPage";
import CandidatoPage from "./pages/CandidatoPage";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<div className="container"><h1>Dashboard</h1><p>Use o menu à esquerda para navegar.</p></div>} />

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