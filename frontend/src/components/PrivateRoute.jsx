import { Navigate } from 'react-router-dom';

export default function PrivateRoute({ children, roles }) {
  const token = localStorage.getItem('token');
  const userRole = localStorage.getItem('role');

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  if (roles && !roles.includes(userRole)) {
    alert("Acesso não autorizado para seu perfil.");
    return <Navigate to="/" replace />;
  }

  return children;
}