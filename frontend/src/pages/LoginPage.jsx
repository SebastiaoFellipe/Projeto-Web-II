import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { loginUser } from '../services/api';

export default function LoginPage() {
  const [formData, setFormData] = useState({ login: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await loginUser(formData);
      localStorage.setItem('token', data.token);
      localStorage.setItem('username', formData.login);
      localStorage.setItem('role', data.role);
      
      navigate('/');
    } catch (err) {
      console.error(err);
      setError('Login falhou. Verifique usuário e senha.');
    }
  };

  return (
    <div style={styles.container} className="flex items-center justify-center w-full">
      <div style={styles.card}>
        <h2 style={{ textAlign: 'center', marginBottom: '1rem' }}>Login Aquário</h2>
        {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}
        <form onSubmit={handleSubmit}>
          <div style={styles.inputGroup}>
            <label>Usuário</label>
            <input
              type="text"
              name="login"
              value={formData.login}
              onChange={handleChange}
              required
              style={styles.input}
            />
          </div>
          <div style={styles.inputGroup}>
            <label>Senha</label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
              style={styles.input}
            />
          </div>
          <button type="submit" className="btn btn-primary" style={{ width: '100%', marginTop: '10px' }}>
            Entrar
          </button>
        </form>
        <p style={{ marginTop: '15px', textAlign: 'center' }}>
          Não tem conta? <Link to="/register">Registre-se</Link>
        </p>
      </div>
    </div>
  );
}

const styles = {
  container: { display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', width: '100vw', background: '#f4f9fb' },
  card: { background: 'white', padding: '2rem', borderRadius: '12px', boxShadow: '0 2px 10px rgba(0,0,0,0.1)', width: '350px' },
  inputGroup: { marginBottom: '15px' },
  input: { width: '100%', padding: '10px', borderRadius: '8px', border: '1px solid #ccc', marginTop: '5px' }
};