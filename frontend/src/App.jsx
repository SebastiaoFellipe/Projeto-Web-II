import { Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import PrivateRoute from "./components/PrivateRoute";

import FuncionarioPage from "./pages/FuncionarioPage";
import ProfessorPage from "./pages/ProfessorPage";
import PalestraPage from "./pages/PalestraPage";
import VisitaPage from "./pages/VisitaPage";
import AnimalPage from "./pages/AnimalPage";
import HabitatPage from "./pages/HabitatPage";
import CandidatoPage from "./pages/CandidatoPage";
import EstoquePage from "./pages/EstoquePage";

export default function App() {
  return (
    <Routes>
      {/* Rotas Públicas */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* Rotas Protegidas */}
      <Route path="/" element={
        <PrivateRoute>
          <Layout />
        </PrivateRoute>
      }>
        <Route index element={<div className="container"><h1>Dashboard</h1><p>Bem-vindo ao Sistema Aquário.</p></div>} />
        
        <Route path="funcionarios" element={<FuncionarioPage />} />
        <Route path="professores" element={<ProfessorPage />} />
        <Route path="palestras" element={<PalestraPage />} />
        <Route path="visitas" element={<VisitaPage />} />
        <Route path="animais" element={<AnimalPage />} />
        <Route path="habitats" element={<HabitatPage />} />
        <Route path="candidatos" element={<CandidatoPage />} />
        <Route path="estoque" element={<EstoquePage />} />
      </Route>
    </Routes>
  );
}