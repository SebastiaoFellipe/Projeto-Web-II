function FormInput({ field, value, onChange }) {
  const handleChange = (e) => onChange(field.name, e.target.value);

  const commonProps = {
    name: field.name,
    value: value ?? "",
    onChange: handleChange,
    placeholder: field.label,
  };

  if (field.type === "select") {
    return (
      <select {...commonProps}>
        <option value="">Selecione...</option>
        {field.options?.map((opt) => (
          <option key={opt.value} value={opt.value}>
            {opt.label}
          </option>
        ))}
      </select>
    );
  }

  return <input type={field.type || "text"} {...commonProps} />;
}

export default function CrudForm({ entity, setEntity, fields, onSubmit, onCancel }) {
  const handleChange = (name, value) => {
    setEntity((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <form onSubmit={onSubmit}>
      {fields.map((f) => (
        <div key={f.name} style={{ marginBottom: 12 }}>
          <label style={{ display: "block", fontWeight: 600, marginBottom: 6 }}>{f.label}</label>
          <FormInput field={f} value={entity[f.name]} onChange={handleChange} />
        </div>
      ))}

      <div style={{ display: "flex", justifyContent: "flex-end", gap: 12, marginTop: 8 }}>
        <button type="button" className="btn" onClick={onCancel} style={{ border: "1px solid #d0d7db" }}>
          Cancelar
        </button>
        <button type="submit" className="btn btn-primary">
          Salvar
        </button>
      </div>
    </form>
  );
}