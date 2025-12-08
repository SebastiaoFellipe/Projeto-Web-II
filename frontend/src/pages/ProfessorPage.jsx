import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/professores";
const ENTITY = "Professor";

const fields = [
  { name: "nome", label: "Nome" },
  { name: "cpf", label: "CPF" },
  { name: "areaAplicada", label: "Área" },
  { name: "nivelAcademico", label: "Nível", type: "select", options: [{ value: "GRADUACAO", label: "Graduação" }, { value: "MESTRADO", label: "Mestrado" }, { value: "DOUTORADO", label: "Doutorado" }] },
  // Como professor herda de funcionario, enviamos os campos padrão ocultos ou pré-definidos se o form não pedir
];

const empty = { nome: "", cpf: "", areaAplicada: "", nivelAcademico: "MESTRADO", cargo: "Professor", tipoVinculo: "FIXO" };

export default function ProfessorPage() {
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
      if (data.content) { setItems(data.content); setTotalPages(data.totalPages); }
      else setItems(data || []);
    } catch (err) { console.error(err); }
  };

  useEffect(() => { fetch(); }, [page]);
  const handleSearch = (e) => { e.preventDefault(); setPage(0); fetch(); };
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = { ...current, cargo: "Professor", tipoVinculo: "FIXO" }; // Defaults
    if (isEditing) await updateItem(API_ENDPOINT, current.id, payload);
    else await createItem(API_ENDPOINT, payload);
    setIsModalOpen(false); fetch();
  };
  const handleDelete = async (id) => { if (confirm("Excluir?")) { await deleteItem(API_ENDPOINT, id); fetch(); } };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h1>Gerenciar {ENTITY}es</h1>
        <button className="btn btn-primary" onClick={() => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); }}>Novo</button>
      </div>
      <div style={{ marginBottom: 20, display: 'flex', gap: 10 }}>
        <input type="text" placeholder="Buscar..." value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} style={{ padding: 8, flex: 1 }} />
        <button className="btn btn-secondary" onClick={handleSearch}>Buscar</button>
      </div>
      <div className="table-container" style={{ overflowX: "auto" }}>
        <table style={{ minWidth: "900px" }}>
          <thead><tr><th>Nome</th><th>CPF</th><th>Área</th><th>Nível</th><th>Cargo</th><th>Vínculo</th><th style={{ textAlign: "right" }}>Ações</th></tr></thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.nome}</td><td>{it.cpf}</td><td>{it.areaAplicada}</td><td>{it.nivelAcademico}</td>
                <td>{it.cargo}</td><td>{it.tipoVinculo}</td>
                <td style={{ textAlign: "right" }}>
                  <button className="btn btn-secondary" onClick={() => { setCurrent(it); setIsEditing(true); setIsModalOpen(true); }}>Editar</button>
                  <button className="btn" style={{ marginLeft:8, background: "#e03131", color: "#fff" }} onClick={() => handleDelete(it.id)}>Excluir</button>
                </td>
              </tr>
            ))}
            {items.length === 0 && <tr><td colSpan={5} style={{ padding: 16 }}>Nenhum registro.</td></tr>}
          </tbody>
        </table>
      </div>
      <div style={{ marginTop: 20, display: 'flex', justifyContent: 'center', gap: 15 }}>
        <button className="btn btn-secondary" disabled={page === 0} onClick={() => setPage(p => p - 1)}>Anterior</button>
        <span>Página {page + 1} de {totalPages || 1}</span>
        <button className="btn btn-secondary" disabled={page >= totalPages - 1} onClick={() => setPage(p => p + 1)}>Próximo</button>
      </div>
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? "Editar" : "Novo"}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSubmit} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}