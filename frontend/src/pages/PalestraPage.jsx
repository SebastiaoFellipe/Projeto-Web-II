import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/palestras";
const ENTITY = "Palestra";

const empty = { 
  tema: "", data: "", horario: "", local: "", 
  palestrante: "", formacaoPalestrante: "", publicoAlvo: "", funcionarioId: "" 
};

export default function PalestraPage() {
  const [items, setItems] = useState([]);
  const [funcionarios, setFuncionarios] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [current, setCurrent] = useState(empty);

  const fields = [
    { name: "tema", label: "Tema" },
    { name: "data", label: "Data", type: "date" },
    { name: "horario", label: "Horário", type: "time" },
    { name: "local", label: "Local" },
    { name: "palestrante", label: "Palestrante Externo" },
    { name: "formacaoPalestrante", label: "Formação Palestrante" },
    { name: "publicoAlvo", label: "Público Alvo" },
    {
      name: "funcionarioId", label: "Funcionário Responsável", type: "select",
      options: funcionarios.map(f => ({ value: f.id, label: f.nome }))
    }
  ];

  const fetch = async () => {
    try {
      const [palestrasData, funcData] = await Promise.all([
        getItens(API_ENDPOINT, { page, size: 5, tema: searchTerm }),
        getItens("/funcionarios", { size: 1000 })
      ]);

      if (palestrasData.content) {
        setItems(palestrasData.content);
        setTotalPages(palestrasData.totalPages);
      } else {
        setItems(palestrasData || []);
      }
      
      setFuncionarios(funcData.content || funcData || []);
    } catch (err) { console.error(err); }
  };

  useEffect(() => { fetch(); }, [page]);
  const handleSearch = (e) => { e.preventDefault(); setPage(0); fetch(); };

  const handleCreate = () => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); };
  
  const handleEdit = (item) => {
    setCurrent({
      ...item,
      funcionarioId: item.funcionario ? item.funcionario.id : ""
    });
    setIsEditing(true); 
    setIsModalOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // O backend espera "funcionarioId" no corpo para fazer o bind
    if (isEditing) {
      await updateItem(API_ENDPOINT, current.id, current);
      alert("Palestra atualizada com sucesso!");
    }
    else {
      await createItem(API_ENDPOINT, current);
      alert("Palestra cadastrada com sucesso!");
    }
    fetch(); setIsModalOpen(false);
  };

  const handleDelete = async (id) => { if (confirm("Excluir?")) { await deleteItem(API_ENDPOINT, id); alert("Palestra excluída com sucesso!"); fetch(); } };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h1>Gerenciar {ENTITY}s</h1>
        <button className="btn btn-primary" onClick={handleCreate}>Nova {ENTITY}</button>
      </div>
      <div style={{ marginBottom: 20, display: 'flex', gap: 10 }}>
        <input type="text" placeholder="Buscar..." value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} style={{ padding: 8, flex: 1 }} />
        <button className="btn btn-secondary" onClick={handleSearch}>Buscar</button>
      </div>
      <div className="table-container" style={{ overflowX: "auto" }}>
        <table style={{ minWidth: "1000px" }}>
          <thead>
            <tr>
              <th>Tema</th><th>Data</th><th>Horário</th><th>Local</th>
              <th>Palestrante</th><th>Formação</th><th>Público</th>
              <th>Func. Responsável</th><th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.tema}</td>
                <td>{it.data}</td>
                <td>{it.horario}</td>
                <td>{it.local}</td>
                <td>{it.palestrante}</td>
                <td>{it.formacaoPalestrante}</td>
                <td>{it.publicoAlvo}</td>
                <td>{it.funcionario ? it.funcionario.nome : "-"}</td>
                <td style={{ textAlign: "right" }}>
                  <button className="btn btn-secondary" onClick={() => handleEdit(it)}>Editar</button>
                  <button className="btn" style={{ marginLeft: 8, background: "#e03131", color: "#fff" }} onClick={() => handleDelete(it.id)}>Excluir</button>
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
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? `Editar ${ENTITY}` : `Nova ${ENTITY}`}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSubmit} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}