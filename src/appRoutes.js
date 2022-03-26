import { Route, HashRouter, Routes } from "react-router-dom";
import CadastroUsuario from "./pages/cadastroUsuario";
import Login from "./pages/login";

export default function AppRoutes() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/cadastro-usuarios" element={<CadastroUsuario />} />
      </Routes>
    </HashRouter>
  );
}
