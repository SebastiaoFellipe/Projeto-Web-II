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
    name: "tipoVinculo", label: "Vínculo", type: "select",
    options: [{ value: "FIXO", label: "Fixo" }, { value: "TEMPORARIO", label: "Temporário" }, { value: "ESTAGIARIO", label: "Estagiário" }]
  },
];

const empty = { nome: "", cpf: "", cargo: "", tipoVinculo: "FIXO" };

export default function FuncionarioPage() {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [current, setCurrent] = useState(empty);
  const [isEditing, setIsEditing] = useState(false);

  const fetch = async () => {
    try {
      const data = await getItens(API_ENDPOINT, { page, size: 5, nome: searchTerm });
      if (data.content) {
        setItems(data.content);
        setTotalPages(data.totalPages);
      } else {
        setItems(data || []);
      }
    } catch (err) { console.error(err); }
  };

  useEffect(() => { fetch(); }, [page]);

  const handleSearch = (e) => { e.preventDefault(); setPage(0); fetch(); };
  const handleSave = async (e) => {
    e.preventDefault();
    if (isEditing) await updateItem(API_ENDPOINT, current.id, current);
    else await createItem(API_ENDPOINT, current);
    setIsModalOpen(false); fetch();
  };
  const handleDelete = async (id) => { if (confirm("Excluir?")) { await deleteItem(API_ENDPOINT, id); fetch(); } };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h1>Gerenciar {ENTITY}s</h1>
        <button className="btn btn-primary" onClick={() => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); }}>Novo</button>
      </div>

      <div style={{ marginBottom: 20, display: 'flex', gap: 10 }}>
        <input type="text" placeholder="Buscar por nome..." value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} style={{ padding: 8, flex: 1, border: '1px solid #ccc', borderRadius: 4 }} />
        <button className="btn btn-secondary" onClick={handleSearch}>Buscar</button>
      </div>

      <div className="table-container">
        <table>
          <thead><tr><th>Nome</th><th>CPF</th><th>Cargo</th><th>Vínculo</th><th>Ações</th></tr></thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.nome}</td><td>{it.cpf}</td><td>{it.cargo}</td><td>{it.tipoVinculo}</td>
                <td>
                  <button className="btn btn-secondary" onClick={() => { setCurrent(it); setIsEditing(true); setIsModalOpen(true); }} style={{marginRight: 8}}>Editar</button>
                  <button className="btn" style={{ background: "#e03131", color: "#fff" }} onClick={() => handleDelete(it.id)}>Excluir</button>
                </td>
              </tr>
            ))}
            {items.length === 0 && <tr><td colSpan={5} style={{ padding: 16 }}>Nenhum registro.</td></tr>}
          </tbody>
        </table>
      </div>

      <div style={{ marginTop: 20, display: 'flex', justifyContent: 'center', gap: 15, alignItems: 'center' }}>
        <button className="btn btn-secondary" disabled={page === 0} onClick={() => setPage(p => p - 1)}>Anterior</button>
        <span>Página {page + 1} de {totalPages || 1}</span>
        <button className="btn btn-secondary" disabled={page >= totalPages - 1} onClick={() => setPage(p => p + 1)}>Próximo</button>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? "Editar" : "Novo"}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSave} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}