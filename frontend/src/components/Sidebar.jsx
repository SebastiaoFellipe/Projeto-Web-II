import { NavLink } from 'react-router-dom';

const links = [
  { name: 'Funcionários', path: '/funcionarios' },
  { name: 'Professores', path: '/professores' },
  { name: 'Palestras', path: '/palestras' },
  { name: 'Visitas', path: '/visitas' },
  { name: 'Animais', path: '/animais' },
  { name: 'Habitats', path: '/habitats' },
  { name: 'Candidatos', path: '/candidatos' },
];

export default function Sidebar() {
  const linkClass = "block px-4 py-2 rounded-md text-gray-700 hover:bg-blue-500 hover:text-white transition-colors";
  const activeLinkClass = "bg-blue-600 text-white";

  return (
    <div className="w-64 h-screen bg-white shadow-lg p-4">
      <h2 className="text-2xl font-bold text-blue-700 mb-6">Aquário</h2>
      <nav>
        <ul>
          {links.map((link) => (
            <li key={link.name} className="mb-2">
              <NavLink
                to={link.path}
                className={({ isActive }) =>
                  `${linkClass} ${isActive ? activeLinkClass : ''}`
                }
              >
                {link.name}
              </NavLink>
            </li>
          ))}
        </ul>
      </nav>
    </div>
  );
}