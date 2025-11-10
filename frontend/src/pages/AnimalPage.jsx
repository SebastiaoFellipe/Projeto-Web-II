import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/animais";
const ENTITY = "Animal";

const fields = [
  { name: "nome", label: "Nome" },
  { name: "nomeCientifico", label: "Nome Científico" },
  { name: "familia", label: "Família" },
  { name: "genero", label: "Gênero" },
  { name: "especie", label: "Espécie" },
  {
    name: "classificacao",
    label: "Classificação",
    type: "select",
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
];

const empty = { nome: "", nomeCientifico: "", familia: "", genero: "", especie: "", classificacao: "NAO_AMEACADO", dieta: "", statusSaude: "", dataEntrada: "", idade: 0 };

export default function AnimalPage() {
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
  const handleEdit = (item) => {
    const formatted = { ...item, dataEntrada: item.dataEntrada ? new Date(item.dataEntrada).toISOString().split("T")[0] : "" };
    setCurrent(formatted);
    setIsEditing(true);
    setIsModalOpen(true);
  };

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

  // --- Função para formatar a data ---
  const formatDate = (dateString) => {
    if (!dateString) return "N/A";
    const date = new Date(dateString);
    date.setDate(date.getDate() + 1); 
    return date.toLocaleDateString("pt-BR");
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
          {/* ===== CABEÇALHO ATUALIZADO ===== */}
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Nome Científico</th>
              <th>Família</th>
              <th>Gênero</th>
              <th>Espécie</th>
              <th>Classificação</th>
              <th>Dieta</th>
              <th>Status de Saúde</th>
              <th>Data de Entrada</th>
              <th>Idade</th>
              <th style={{ textAlign: "right" }}>Ações</th>
            </tr>
          </thead>
          
          {/* ===== CORPO DA TABELA ATUALIZADO ===== */}
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.id}</td>
                <td>{it.nome}</td>
                <td>{it.nomeCientifico}</td>
                <td>{it.familia}</td>
                <td>{it.genero}</td>
                <td>{it.especie}</td>
                <td>{it.classificacao}</td>
                <td>{it.dieta}</td>
                <td>{it.statusSaude}</td>
                <td>{formatDate(it.dataEntrada)}</td> {/* Data formatada */}
                <td>{it.idade}</td>
                <td style={{ textAlign: "right" }}>
                  <button className="btn btn-secondary" onClick={() => handleEdit(it)} style={{ marginRight: 8 }}>Editar</button>
                  <button className="btn" onClick={() => handleDelete(it.id)} style={{ background: "#e03131", color: "#fff", border: "none" }}>Excluir</button>
                </td>
              </tr>
            ))}
            
            {/* ===== COLSPAN ATUALIZADO ===== */}
            {items.length === 0 && (
              <tr>
                <td colSpan={12} style={{ padding: 16 }}> {/* MUDADO DE 6 PARA 12 */}
                  Nenhum registro.
                </td>
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