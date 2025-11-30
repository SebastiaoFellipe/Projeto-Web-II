import { NavLink } from "react-router-dom";

export default function Sidebar() {
  const links = [
    { to: "/", label: "Dashboard" },
    { to: "/funcionarios", label: "Funcionários" },
    { to: "/professores", label: "Professores" },
    { to: "/palestras", label: "Palestras" },
    { to: "/visitas", label: "Visitas" },
    { to: "/animais", label: "Animais" },
    { to: "/habitats", label: "Habitats" },
    { to: "/candidatos", label: "Candidatos" },
  ];

  return (
    <aside className="sidebar" aria-label="Sidebar">
      <div style={{ padding: "0 20px 12px 20px" }}>
        <h2 style={{ fontSize: 20, fontWeight: 800, marginBottom: 8 }}>Aquário</h2>
        <p style={{ fontSize: 12, opacity: 0.9 }}>Painel Administrativo</p>
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

      <div style={{ marginTop: "auto", padding: 16, fontSize: 12, opacity: 0.9 }}>
        Versão 1.0 • Admin
      </div>
    </aside>
  );
}