import { useEffect, useState } from "react";
import { getItens } from "../services/api";

export default function DashboardPage() {
  const [stats, setStats] = useState({
    funcionarios: 0,
    professores: 0,
    palestras: 0,
    visitas: 0,
    animais: 0,
    habitats: 0,
    candidatos: 0,
    estoque: 0
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchStats() {
      try {
        const endpoints = [
          { key: 'funcionarios', url: '/funcionarios' },
          { key: 'professores', url: '/professores' },
          { key: 'palestras', url: '/palestras' },
          { key: 'visitas', url: '/visitas' },
          { key: 'animais', url: '/animais' },
          { key: 'habitats', url: '/habitats' },
          { key: 'candidatos', url: '/candidatos' },
          { key: 'estoque', url: '/estoques' }
        ];

        const promises = endpoints.map(ep => getItens(ep.url, { size: 1 }));
        const results = await Promise.all(promises);

        const newStats = {};
        results.forEach((res, index) => {
          const count = res.totalElements !== undefined ? res.totalElements : (Array.isArray(res) ? res.length : 0);
          newStats[endpoints[index].key] = count;
        });

        setStats(newStats);
      } catch (err) {
        console.error("Erro ao carregar estatísticas:", err);
      } finally {
        setLoading(false);
      }
    }

    fetchStats();
  }, []);

  const cards = [
    { title: "Funcionários", count: stats.funcionarios, color: "#005f86" },
    { title: "Professores", count: stats.professores, color: "#005f86" },
    { title: "Palestras", count: stats.palestras, color: "#005f86" },
    { title: "Visitas", count: stats.visitas, color: "#005f86" },
    { title: "Animais", count: stats.animais, color: "#005f86" },
    { title: "Habitats", count: stats.habitats, color: "#005f86" },
    { title: "Candidatos", count: stats.candidatos, color: "#005f86" },
    { title: "Estoque", count: stats.estoque, color: "#005f86" },
  ];

  return (
    <div>
      <h1 style={{ marginBottom: "2rem" }}>Visão Geral do Sistema</h1>
      
      {loading ? (
        <p>Carregando indicadores...</p>
      ) : (
        <div style={styles.grid}>
          {cards.map((card, index) => (
            <div key={index} style={styles.card}>
              <div style={{ ...styles.iconPlaceholder, backgroundColor: card.color }}></div>
              <div>
                <h3 style={styles.cardTitle}>{card.title}</h3>
                <p style={styles.cardCount}>{card.count}</p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

const styles = {
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(2, 1fr)",
    gap: "20px",
  },
  card: {
    background: "#fff",
    padding: "24px",
    borderRadius: "12px",
    boxShadow: "0 4px 6px rgba(0,0,0,0.05)",
    display: "flex",
    alignItems: "center",
    gap: "20px",
    transition: "transform 0.2s",
    cursor: "default"
  },
  iconPlaceholder: {
    width: "60px",
    height: "60px",
    borderRadius: "12px",
    opacity: 0.9
  },
  cardTitle: {
    margin: 0,
    fontSize: "1.1rem",
    color: "#666",
    fontWeight: "600"
  },
  cardCount: {
    margin: 0,
    fontSize: "2rem",
    fontWeight: "bold",
    color: "#333"
  }
};