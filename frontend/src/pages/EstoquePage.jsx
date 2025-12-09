import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/estoques";
const ENTITY = "Item";

const fields = [
  { name: "nomeProduto", label: "Produto" },
  { name: "codigoProduto", label: "Código" },
  { name: "quantidade", label: "Qtd", type: "number" },
  { name: "unidadeMedida", label: "Unidade" },
  { name: "dataValidade", label: "Validade", type: "date" },
];

const empty = { nomeProduto: "", codigoProduto: "", quantidade: 0, unidadeMedida: "", dataValidade: "" };

export default function EstoquePage() {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [current, setCurrent] = useState(empty);
  const [isEditing, setIsEditing] = useState(false);

  const fetch = async () => {
    try {
      const data = await getItens(API_ENDPOINT, { page, size: 5, nomeProduto: searchTerm });
      if (data.content) { setItems(data.content); setTotalPages(data.totalPages); }
      else setItems(data || []);
    } catch (err) { console.error(err); }
  };

  useEffect(() => { fetch(); }, [page]);
  const handleSearch = (e) => { e.preventDefault(); setPage(0); fetch(); };

  const handleSave = async (e) => {
    e.preventDefault();
    if (isEditing) {
      await updateItem(API_ENDPOINT, current.id, current);
      alert("Item atualizado com sucesso!");
    }
    else {
      await createItem(API_ENDPOINT, current);
      alert("Item cadastrado com sucesso!");
    }
    setIsModalOpen(false); fetch();
  };
  const handleDelete = async (id) => { if (confirm("Excluir?")) { await deleteItem(API_ENDPOINT, id); alert("Item excluído com sucesso!"); fetch(); } };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h1>Gerenciar Estoque</h1>
        <button className="btn btn-primary" onClick={() => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); }}>Novo</button>
      </div>
      <div style={{ marginBottom: 20, display: 'flex', gap: 10 }}>
        <input type="text" placeholder="Buscar produto..." value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} style={{ padding: 8, flex: 1, border: '1px solid #ccc', borderRadius: 4 }} />
        <button className="btn btn-secondary" onClick={handleSearch}>Buscar</button>
      </div>
      <div className="table-container" style={{ overflowX: "auto" }}>
        <table>
          <thead><tr><th>Produto</th><th>Código</th><th>Qtd</th><th>Validade</th><th>Ações</th></tr></thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.nomeProduto}</td><td>{it.codigoProduto}</td><td>{it.quantidade} {it.unidadeMedida}</td><td>{it.dataValidade}</td>
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