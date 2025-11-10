import { useState, useEffect } from 'react';
import { getItens, createItem, updateItem, deleteItem } from '../services/api';
import Modal from '../components/Modal';
import CrudForm from '../components/CrudForm';

// Configuração da entidade
const API_ENDPOINT = '/funcionarios';
const ENTITY_NAME = 'Funcionário';

const funcionarioFields = [
  { name: 'nome', label: 'Nome' },
  { name: 'cpf', label: 'CPF' },
  { name: 'cargo', label: 'Cargo' },
  { 
    name: 'tipoVinculo', 
    label: 'Vínculo',
    type: 'select',
    options: [
        { value: 'FIXO', label: 'Fixo' },
        { value: 'TEMPORARIO', label: 'Temporário' },
        { value: 'ESTAGIARIO', label: 'Estagiário' }
    ]
  },
];

// Colunas da tabela
const tableColumns = [
  { key: 'id', label: 'ID' },
  { key: 'nome', label: 'Nome' },
  { key: 'cpf', label: 'CPF' },
  { key: 'cargo', label: 'Cargo' },
  { key: 'tipoVinculo', label: 'Vínculo' },
];

const emptyForm = { nome: '', cpf: '', cargo: '', tipoVinculo: '' };

// Componente da Página
export default function FuncionarioPage() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [currentItem, setCurrentItem] = useState(emptyForm);

  // --- API ---
  const fetchData = async () => {
    try {
      setLoading(true);
      const data = await getItens(API_ENDPOINT);
      setItems(data);
      setError(null);
    } catch (err) { setError('Erro ao buscar dados: ' + err.message); } 
    finally { setLoading(false); }
  };

  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await updateItem(API_ENDPOINT, currentItem.id, currentItem);
      } else {
        await createItem(API_ENDPOINT, currentItem);
      }
      fetchData();
      handleCancel();
    } catch (err) { setError('Erro ao salvar: ' + err.message); }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja excluir?')) {
      try {
        await deleteItem(API_ENDPOINT, id);
        fetchData();
      } catch (err) { setError('Erro ao excluir: ' + err.message); }
    }
  };

  // --- Controle do Formulário ---
  const handleCreate = () => {
    setCurrentItem(emptyForm);
    setIsEditing(false);
    setIsModalOpen(true);
  };

  const handleEdit = (item) => {
    setCurrentItem(item);
    setIsEditing(true);
    setIsModalOpen(true);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
    setCurrentItem(emptyForm);
  };

  // --- Renderização ---
  if (loading) return <p>Carregando...</p>;

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-4xl font-bold">Gerenciar {ENTITY_NAME}s</h1>
        <button 
          onClick={handleCreate} 
          className="px-4 py-2 bg-blue-600 text-white rounded-lg font-semibold hover:bg-blue-700"
        >
          Novo {ENTITY_NAME}
        </button>
      </div>

      {error && <p className="text-red-500 bg-red-100 p-3 rounded mb-4">{error}</p>}

      {/* Tabela */}
      <div className="bg-white shadow-md rounded-lg overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              {tableColumns.map(col => (
                <th key={col.key} className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                  {col.label}
                </th>
              ))}
              <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase">Ações</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {items.map((item) => (
              <tr key={item.id} className="hover:bg-gray-50">
                {tableColumns.map(col => (
                  <td key={col.key} className="px-6 py-4 whitespace-nowrap">{item[col.key]}</td>
                ))}
                <td className="px-6 py-4 text-right space-x-2 whitespace-nowrap">
                  <button onClick={() => handleEdit(item)} className="px-3 py-1 bg-yellow-500 text-white rounded text-sm font-medium hover:bg-yellow-600">Editar</button>
                  <button onClick={() => handleDelete(item.id)} className="px-3 py-1 bg-red-600 text-white rounded text-sm font-medium hover:bg-red-700">Excluir</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal */}
      <Modal 
        isOpen={isModalOpen} 
        onClose={handleCancel}
        title={isEditing ? `Editar ${ENTITY_NAME}` : `Novo ${ENTITY_NAME}`}
      >
        <CrudForm
          entity={currentItem}
          setEntity={setCurrentItem}
          fields={funcionarioFields}
          onSubmit={handleSubmit}
          onCancel={handleCancel}
        />
      </Modal>
    </div>
  );
}