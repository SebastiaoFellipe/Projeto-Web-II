import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/funcionarios";
const ENTITY = "Funcionário";

const fields = [
  { name: "nome", label: "Nome" },
  { name: "cpf", label: "CPF" },
  { name: "cargo", label: "Cargo" },
  {
    name: "tipoVinculo",
    label: "Vínculo",
    type: "select",
    options: [
      { value: "FIXO", label: "Fixo" },
      { value: "TEMPORARIO", label: "Temporário" },
      { value: "ESTAGIARIO", label: "Estagiário" },
    ],
  },
];

const empty = { nome: "", cpf: "", cargo: "", tipoVinculo: "" };

export default function FuncionarioPage() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [current, setCurrent] = useState(empty);

  const fetch = async () => {
    try {
      setLoading(true);
      const data = await getItens(API_ENDPOINT);
      setItems(data || []);
      setError(null);
    } catch (err) {
      setError("Erro ao buscar: " + err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetch();
    // eslint-disable-next-line
  }, []);

  const handleCreate = () => {
    setCurrent(empty);
    setIsEditing(false);
    setIsModalOpen(true);
  };

  const handleEdit = (item) => {
    setCurrent(item);
    setIsEditing(true);
    setIsModalOpen(true);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Tem certeza que deseja excluir?")) return;
    try {
      await deleteItem(API_ENDPOINT, id);
      fetch();
    } catch (err) {
      setError("Erro ao excluir: " + err.message);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await updateItem(API_ENDPOINT, current.id, current);
      } else {
        await createItem(API_ENDPOINT, current);
      }
      fetch();
      setIsModalOpen(false);
    } catch (err) {
      setError("Erro ao salvar: " + err.message);
    }
  };

  if (loading) return <div className="container">Carregando...</div>;

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 16 }}>
        <h1>Gerenciar {ENTITY}s</h1>
        <button className="btn btn-primary" onClick={handleCreate}>Adicionar {ENTITY}</button>
      </div>

      {error && <div style={{ color: "crimson", marginBottom: 12 }}>{error}</div>}

      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>CPF</th>
              <th>Cargo</th>
              <th>Vínculo</th>
              <th style={{ textAlign: "right" }}>Ações</th>
            </tr>
          </thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.id}</td>
                <td>{it.nome}</td>
                <td>{it.cpf}</td>
                <td>{it.cargo}</td>
                <td>{it.tipoVinculo}</td>
                <td style={{ textAlign: "right" }}>
                  <button className="btn btn-secondary" onClick={() => handleEdit(it)} style={{ marginRight: 8 }}>
                    Editar
                  </button>
                  <button
                    className="btn"
                    onClick={() => handleDelete(it.id)}
                    style={{ background: "#e03131", color: "#fff", border: "none" }}
                  >
                    Excluir
                  </button>
                </td>
              </tr>
            ))}
            {items.length === 0 && (
              <tr>
                <td colSpan={6} style={{ padding: 16 }}>Nenhum registro encontrado.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? `Editar ${ENTITY}` : `Novo ${ENTITY}`}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSubmit} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}