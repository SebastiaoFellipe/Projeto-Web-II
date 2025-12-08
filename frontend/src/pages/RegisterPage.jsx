import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { registerUser } from '../services/api';

export default function RegisterPage() {
  const [formData, setFormData] = useState({ 
    login: '', 
    password: '', 
    role: 'FUNCIONARIO_COMUM'
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registerUser(formData);
      alert('Usuário registrado com sucesso!');
      navigate('/login');
    } catch (err) {
      console.error(err);
      setError('Erro ao registrar. O usuário pode já existir.');
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2 style={{ textAlign: 'center', marginBottom: '1rem' }}>Novo Usuário</h2>
        {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}
        <form onSubmit={handleSubmit}>
          <div style={styles.inputGroup}>
            <label>Login</label>
            <input type="text" name="login" value={formData.login} onChange={handleChange} required style={styles.input} />
          </div>
          <div style={styles.inputGroup}>
            <label>Senha</label>
            <input type="password" name="password" value={formData.password} onChange={handleChange} required style={styles.input} />
          </div>
          <div style={styles.inputGroup}>
            <label>Tipo de Usuário</label>
            <select name="role" value={formData.role} onChange={handleChange} style={styles.input}>
              <option value="ADMIN">Administrador</option>
              <option value="FUNCIONARIO_COMUM">Funcionário</option>
              <option value="PROFESSOR">Professor</option>
              <option value="CANDIDATO">Candidato</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary" style={{ width: '100%', marginTop: '10px' }}>
            Cadastrar
          </button>
        </form>
        <p style={{ marginTop: '15px', textAlign: 'center' }}>
          Já tem conta? <Link to="/login">Faça Login</Link>
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