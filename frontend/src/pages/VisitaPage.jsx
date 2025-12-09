import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/visitas";
const ENTITY = "Visita";

const empty = { 
  nome: "", data: "", hora: "", instituicao: "", 
  tipoInstituicao: "", professorResponsavel: "", 
  qtdAlunos: 5,
  telefone: "", funcionarioIds: [] 
};

export default function VisitaPage() {
  const [items, setItems] = useState([]);
  const [funcionarios, setFuncionarios] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [current, setCurrent] = useState(empty);
  const [error, setError] = useState(null);

  const fields = [
    { name: "nome", label: "Nome da Visita" },
    { name: "data", label: "Data", type: "date" },
    { name: "hora", label: "Hora", type: "time" },
    { name: "instituicao", label: "Instituição" },
    { name: "tipoInstituicao", label: "Tipo Inst." },
    { name: "professorResponsavel", label: "Prof. Responsável" },
    { name: "qtdAlunos", label: "Qtd. Alunos (3-100)", type: "number", min: 3, max: 100 },
    { name: "telefone", label: "Telefone" },
    { 
      name: "funcionarioIds", label: "Funcionários (Segure Ctrl para múltiplos)", type: "select", multiple: true,
      options: funcionarios.map(f => ({ value: f.id, label: f.nome }))
    }
  ];

  const fetch = async () => {
    try {
      const [visitasData, funcData] = await Promise.all([
        getItens(API_ENDPOINT, { page, size: 5, nome: searchTerm }),
        getItens("/funcionarios", { size: 1000 })
      ]);

      if (visitasData.content) {
        setItems(visitasData.content);
        setTotalPages(visitasData.totalPages);
      } else {
        setItems(visitasData || []);
      }

      const listaFuncs = funcData.content || funcData || [];
      setFuncionarios(listaFuncs);
      setError(null);
    } catch (err) { 
      setError("Erro ao carregar dados.");
      console.error(err); 
    }
  };

  useEffect(() => { fetch(); }, [page]);
  const handleSearch = (e) => { e.preventDefault(); setPage(0); fetch(); };

  const handleCreate = () => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); };
  
  const toInputDate = (dateStr) => {
    if (!dateStr) return "";

    if (dateStr.match(/^\d{4}-\d{2}-\d{2}/)) {
      return dateStr.substring(0, 10);
    }
    
    if (dateStr.match(/^\d{2}\/\d{2}\/\d{4}$/)) {
      const [day, month, year] = dateStr.split('/');
      return `${year}-${month}-${day}`;
    }

    return dateStr;
  };

  const handleEdit = (item) => {
    const ids = item.funcionarios ? item.funcionarios.map(f => f.id) : [];
    
    setCurrent({ 
      ...item,
      data: toInputDate(item.data),
      funcionarioIds: ids 
    });
    
    setIsEditing(true); 
    setIsModalOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const params = new URLSearchParams();
      if (current.funcionarioIds && Array.isArray(current.funcionarioIds)) {
        current.funcionarioIds.forEach(id => params.append('funcionarioIds', id));
      }

      const config = { params: params };

      if (isEditing) {
        await updateItem(API_ENDPOINT, current.id, current, config);
        alert("Visita atualizada com sucesso!");
      } else {
        await createItem(API_ENDPOINT, current, config);
        alert("Visita cadastrada com sucesso!");
      }
      
      fetch(); 
      setIsModalOpen(false);
    } catch (err) { 
      const msg = err.response?.data?.message || err.message;
      alert("Erro ao salvar: " + msg); 
    }
  };
  
  const handleDelete = async (id) => { if(confirm("Excluir?")) { await deleteItem(API_ENDPOINT, id); alert("Visita excluída com sucesso!"); fetch(); } };

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

      {error && <p style={{color: 'red'}}>{error}</p>}

      <div className="table-container" style={{ overflowX: "auto" }}>
        <table style={{ minWidth: "1200px" }}>
          <thead>
            <tr>
              <th>Nome</th><th>Data</th><th>Hora</th><th>Instituição</th>
              <th>Tipo</th><th>Prof. Resp.</th><th>Alunos</th><th>Tel</th>
              <th>Funcionários</th><th style={{ textAlign: "right" }}>Ações</th>
            </tr>
          </thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.nome}</td>
                <td>{it.data}</td>
                <td>{it.hora}</td><td>{it.instituicao}</td>
                <td>{it.tipoInstituicao}</td><td>{it.professorResponsavel}</td><td>{it.qtdAlunos}</td><td>{it.telefone}</td>
                <td>{it.funcionarios ? it.funcionarios.map(f => f.nome).join(", ") : "-"}</td>
                <td style={{ textAlign: "right", minWidth: "160px" }}>
                  <button className="btn btn-secondary" onClick={() => handleEdit(it)}>Editar</button>
                  <button className="btn" style={{ marginLeft: 8, background: "#e03131", color: "#fff" }} onClick={() => handleDelete(it.id)}>Excluir</button>
                </td>
              </tr>
            ))}
            {items.length === 0 && <tr><td colSpan={10} style={{ padding: 16 }}>Nenhum registro.</td></tr>}
          </tbody>
        </table>
      </div>
      <div style={{ marginTop: 20, display: 'flex', justifyContent: 'center', gap: 15 }}>
        <button className="btn btn-secondary" disabled={page === 0} onClick={() => setPage(p => p - 1)}>Anterior</button>
        <span>Página {page + 1} de {totalPages || 1}</span>
        <button className="btn btn-secondary" disabled={page >= totalPages - 1} onClick={() => setPage(p => p + 1)}>Próximo</button>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? "Editar" : "Nova"}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSubmit} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}