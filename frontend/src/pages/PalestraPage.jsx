import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/palestras";
const ENTITY = "Palestra";

const fields = [
  { name: "tema", label: "Tema" },
  { name: "data", label: "Data", type: "date" },
  { name: "horario", label: "Horário", type: "time" },
  { name: "local", label: "Local" },
  { name: "palestrante", label: "Palestrante" },
];

const empty = { tema: "", data: "", horario: "", local: "", palestrante: "" };

export default function PalestraPage() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [current, setCurrent] = useState(empty);

  const fetch = async () => {
    try { setLoading(true); const data = await getItens(API_ENDPOINT); setItems(data || []); setError(null); }
    catch (err) { setError("Erro ao buscar: " + err.message); } finally { setLoading(false); }
  };

  useEffect(() => { fetch(); }, []);

  const handleCreate = () => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); };
  const handleEdit = (item) => { setCurrent(item); setIsEditing(true); setIsModalOpen(true); };

  const handleDelete = async (id) => {
    if (!window.confirm("Tem certeza?")) return;
    try { await deleteItem(API_ENDPOINT, id); fetch(); } catch (err) { setError("Erro ao excluir: " + err.message); }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) await updateItem(API_ENDPOINT, current.id, current);
      else await createItem(API_ENDPOINT, current);
      fetch(); setIsModalOpen(false);
    } catch (err) { setError("Erro ao salvar: " + err.message); }
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
          <thead><tr><th>ID</th><th>Tema</th><th>Data</th><th>Local</th><th>Palestrante</th><th style={{ textAlign: "right" }}>Ações</th></tr></thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.id}</td>
                <td>{it.tema}</td>
                <td>{it.data}</td>
                <td>{it.local}</td>
                <td>{it.palestrante}</td>
                <td style={{ textAlign: "right" }}>
                  <button className="btn btn-secondary" onClick={() => handleEdit(it)} style={{ marginRight: 8 }}>Editar</button>
                  <button className="btn" onClick={() => handleDelete(it.id)} style={{ background: "#e03131", color: "#fff", border: "none" }}>Excluir</button>
                </td>
              </tr>
            ))}
            {items.length === 0 && <tr><td colSpan={6} style={{ padding: 16 }}>Nenhum registro.</td></tr>}
          </tbody>
        </table>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? `Editar ${ENTITY}` : `Nova ${ENTITY}`}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSubmit} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}