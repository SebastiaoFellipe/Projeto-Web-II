import { NavLink, useNavigate } from "react-router-dom";

export default function Sidebar() {
  const navigate = useNavigate();
  const username = localStorage.getItem('username') || 'Usuário';

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    
    navigate("/login");
  };

  const links = [
    { to: "/", label: "Dashboard" },
    { to: "/funcionarios", label: "Funcionários" },
    { to: "/professores", label: "Professores" },
    { to: "/palestras", label: "Palestras" },
    { to: "/visitas", label: "Visitas" },
    { to: "/animais", label: "Animais" },
    { to: "/habitats", label: "Habitats" },
    { to: "/candidatos", label: "Candidatos" },
    { to: "/estoque", label: "Estoque" }, 
  ];

  return (
    <aside className="sidebar" aria-label="Sidebar">
      <div style={{ padding: "0 20px 12px 20px" }}>
        <h2 style={{ fontSize: 20, fontWeight: 800, marginBottom: 8 }}>Aquário</h2>
        <div style={{ marginBottom: 15, padding: 10, background: 'rgba(255,255,255,0.1)', borderRadius: 8 }}>
          <small style={{ display: 'block', opacity: 0.8 }}>Olá,</small>
          <strong>{username}</strong>
        </div>
      </div>

      <nav style={{ padding: "8px 16px", display: "flex", flexDirection: "column", gap: 8 }}>
        {links.map((l) => (
          <NavLink
            to={l.to}
            key={l.to}
            end={l.to === "/"}
            className={({ isActive }) =>
              isActive ? "sidebar-link active" : "sidebar-link"
            }
            style={{ textDecoration: "none" }}
          >
            {l.label}
          </NavLink>
        ))}
      </nav>

      <div style={{ marginTop: "auto", padding: 16 }}>
        <button 
          onClick={handleLogout}
          style={{ width: '100%', padding: '10px', background: '#e03131', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' }}
        >
          Sair
        </button>
      </div>
    </aside>
  );
}