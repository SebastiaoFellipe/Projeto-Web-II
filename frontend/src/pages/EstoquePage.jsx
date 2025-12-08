import { useEffect, useState } from "react";
import { getItens, createItem, updateItem, deleteItem } from "../services/api";
import Modal from "../components/Modal";
import CrudForm from "../components/CrudForm";

const API_ENDPOINT = "/estoques";
const ENTITY = "Item de Estoque";

const fields = [
  { name: "nomeProduto", label: "Produto" },
  { name: "codigoProduto", label: "Código" },
  { name: "quantidade", label: "Quantidade", type: "number" },
  { name: "unidadeMedida", label: "Unidade (kg, un, l)" },
  { name: "dataValidade", label: "Validade", type: "date" },
];

const empty = { nomeProduto: "", codigoProduto: "", quantidade: 0, unidadeMedida: "", dataValidade: "" };

export default function EstoquePage() {
  const [items, setItems] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [current, setCurrent] = useState(empty);
  const [isEditing, setIsEditing] = useState(false);

  const fetch = async () => {
    const data = await getItens(API_ENDPOINT);
    setItems(data || []);
  };

  useEffect(() => { fetch(); }, []);

  const handleSave = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) await updateItem(API_ENDPOINT, current.id, current);
      else await createItem(API_ENDPOINT, current);
      alert("Salvo com sucesso!");
      setIsModalOpen(false);
      fetch();
    } catch (err) {
      alert("Erro ao salvar: " + err.message);
    }
  };

  const handleDelete = async (id) => {
    if (confirm("Tem certeza?")) {
      await deleteItem(API_ENDPOINT, id);
      fetch();
    }
  };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h1>Gerenciar Estoque</h1>
        <button className="btn btn-primary" onClick={() => { setCurrent(empty); setIsEditing(false); setIsModalOpen(true); }}>Novo Item</button>
      </div>

      <div className="table-container">
        <table>
          <thead>
            <tr><th>Produto</th><th>Código</th><th>Qtd</th><th>Validade</th><th>Ações</th></tr>
          </thead>
          <tbody>
            {items.map((it) => (
              <tr key={it.id}>
                <td>{it.nomeProduto}</td>
                <td>{it.codigoProduto}</td>
                <td>{it.quantidade} {it.unidadeMedida}</td>
                <td>{it.dataValidade}</td>
                <td>
                  <button className="btn btn-secondary" onClick={() => { setCurrent(it); setIsEditing(true); setIsModalOpen(true); }}>Editar</button>
                  <button className="btn" style={{ marginLeft: 8, background: "#e03131", color: "#fff" }} onClick={() => handleDelete(it.id)}>Excluir</button>
                </td>
              </tr>
            ))}
            {items.length === 0 && (
              <tr>
                <td colSpan={12} style={{ padding: 16 }}>
                  Nenhum registro.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title={isEditing ? "Editar" : "Novo"}>
        <CrudForm entity={current} setEntity={setCurrent} fields={fields} onSubmit={handleSave} onCancel={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}