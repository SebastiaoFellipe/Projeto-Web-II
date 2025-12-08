import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/animais";
const ENTITY = "Animal";

const empty = { 
  nome: "", nomeCientifico: "", familia: "", genero: "", especie: "", 
  classificacao: "NAO_AMEACADO", dieta: "", statusSaude: "", 
  dataEntrada: "", idade: 0, habitatId: "" 
};

export default function AnimalPage() {
  const [items, setItems] = useState([]);
  const [habitats, setHabitats] = useState([]); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [current, setCurrent] = useState(empty);

  const fields = [
    { name: "nome", label: "Nome" },
    { name: "nomeCientifico", label: "Nome Científico" },
    { name: "familia", label: "Família" },
    { name: "genero", label: "Gênero" },
    { name: "especie", label: "Espécie" },
    {
      name: "classificacao", label: "Classificação", type: "select",
      options: [
        { value: "NAO_AMEACADO", label: "Não Ameaçado" },
        { value: "AMEACADO", label: "Ameaçado" },
        { value: "EXTINTO", label: "Extinto" },
      ],
    },
    { name: "dieta", label: "Dieta" },
    { name: "statusSaude", label: "Status de Saúde" },
    { name: "dataEntrada", label: "Data de Entrada", type: "date" },
    { name: "idade", label: "Idade", type: "number" },
    {
      name: "habitatId", label: "Habitat", type: "select",
      options: habitats.map(h => ({ value: h.id, label: `${h.descricao} (${h.tipoAmbiente})` }))
    }
  ];

  const fetch = async () => {
    try {
      setLoading(true);
      const [animaisData, habitatsData] = await Promise.all([
        getItens(API_ENDPOINT, { page, size: 5, nome: searchTerm }),
        getItens("/habitats")
      ]);

      if (animaisData.content) {
        setItems(animaisData.content);
        setTotalPages(animaisData.totalPages);
      } else {
        setItems(animaisData || []);
      }

      const listaHabitats = habitatsData.content || habitatsData || [];
      setHabitats(listaHabitats);
      
      setError(null);
    } catch (err) { 
      console.error(err);
      setError("Erro ao carregar dados."); 
    } finally { 
      setLoading(false); 
    }
  };

  useEffect(() => { fetch(); }, [page]);
  const handleSearch = (e) => { e.preventDefault(); setPage(0); fetch(); };

  const toInputDate = (dateStr) => {
    if (!dateStr) return "";
    if (String(dateStr).match(/^\d{4}-\d{2}-\d{2}/)) {
      return String(dateStr).substring(0, 10);
    }
    if (String(dateStr).match(/^\d{2}\/\d{2}\/\d{4}$/)) {
      const [day, month, year] = dateStr.split('/');
      return `${year}-${month}-${day}`;
    }
    return dateStr;
  };

  const handleEdit = (item) => {
    const habId = item.habitat ? item.habitat.id : "";
    
    setCurrent({ 
      ...item, 
      dataEntrada: toInputDate(item.dataEntrada),
      habitatId: habId 
    });
    
    setIsEditing(true); 
    setIsModalOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const habitatSelecionado = habitats.find(h => String(h.id) === String(current.habitatId));
      
      const payload = { 
        ...current, 
        habitat: habitatSelecionado ? { id: habitatSelecionado.id } : null 
      };
      
      if (isEditing) await updateItem(API_ENDPOINT, current.id, payload);
      else await createItem(API_ENDPOINT, payload);
      
      fetch(); 
      setIsModalOpen(false);
    } catch (err) { 
      const msg = err.response?.data?.message || err.message;
      alert("Erro ao salvar: " + msg); 
    }
  };

  const handleCreate = () => { setCurrent({ ...empty, habitatId: "" }); setIsEditing(false); setIsModalOpen(true); };
  const handleDelete = async (id) => {
    if (!window.confirm("Tem certeza que deseja excluir?")) return;
    try { await deleteItem(API_ENDPOINT, id); fetch(); } catch (err) { alert(err.message); }
  };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h1>Gerenciar {ENTITY}is</h1>
        <button className="btn btn-primary" onClick={handleCreate}>Novo {ENTITY}</button>
      </div>

      <div style={{ marginBottom: 20, display: 'flex', gap: 10 }}>
        <input type="text" placeholder="Buscar por nome..." value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} style={{ padding: 8, flex: 1, border: "1px solid #ccc", borderRadius: 4 }} />
        <button className="btn btn-secondary" onClick={handleSearch}>Buscar</button>
      </div>

      {error && <div style={{ color: "crimson", marginBottom: 12 }}>{error}</div>}

      <div className="table-container" style={{ overflowX: "auto" }}>
        <table style={{ minWidth: "1200px" }}>
          <thead>
            <tr>
              <th>Nome</th>
              <th>Científico</th>
              <th>Espécie</th>
              <th>Classificação</th>
              <th>Entrada</th>
              <th>Habitat</th>
              <th style={{ textAlign: "right" }}>Ações</th>
            </tr>
          </thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.nome}</td>
                <td>{it.nomeCientifico}</td>
                <td>{it.especie}</td>
                <td>{it.classificacao}</td>
                <td>{it.dataEntrada}</td>
                <td>{it.habitat ? it.habitat.descricao : "-"}</td>
                <td style={{ textAlign: "right", minWidth: "160px" }}>
                  <button className="btn btn-secondary" onClick={() => handleEdit(it)} style={{ marginRight: 8 }}>Editar</button>
                  <button className="btn" onClick={() => handleDelete(it.id)} style={{ background: "#e03131", color: "#fff" }}>Excluir</button>
                </td>
              </tr>
            ))}
            {items.length === 0 && !loading && (
              <tr><td colSpan={7} style={{textAlign: "center", padding: 20}}>Nenhum registro encontrado.</td></tr>
            )}
          </tbody>
        </table>
      </div>
      
      <div style={{ marginTop: 20, display: 'flex', justifyContent: 'center', gap: 15, alignItems: 'center' }}>
        <button className="btn btn-secondary" disabled={page === 0} onClick={() => setPage(p => p - 1)}>Anterior</button>
        <span>Página {page + 1} de {totalPages || 1}</span>
        <button className="btn btn-secondary" disabled={page >= totalPages - 1} onClick={() => setPage(p => p + 1)}>Próximo</button>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? `Editar ${ENTITY}` : `Novo ${ENTITY}`}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSubmit} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}