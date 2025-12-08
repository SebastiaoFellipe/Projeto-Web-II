function FormInput({ field, value, onChange }) {
  const handleChange = (e) => {
    // Tratamento para multi-select
    if (field.multiple) {
      const selectedOptions = Array.from(e.target.selectedOptions, option => option.value);
      onChange(field.name, selectedOptions);
    } else {
      onChange(field.name, e.target.value);
    }
  };

  const commonProps = {
    id: field.name,
    name: field.name,
    className: "form-control",
    style: { width: "100%", padding: "8px", marginBottom: "10px", borderRadius: "4px", border: "1px solid #ccc" }
  };

  if (field.type === "select") {
    return (
      <select
        {...commonProps}
        value={value || (field.multiple ? [] : "")}
        onChange={handleChange}
        multiple={field.multiple}
      >
        <option value="" disabled={field.multiple}>
          {field.multiple ? "Selecione (Segure Ctrl para múltiplos)" : "Selecione..."}
        </option>
        {field.options?.map((opt) => (
          <option key={opt.value} value={opt.value}>
            {opt.label}
          </option>
        ))}
      </select>
    );
  }

  if (field.type === "textarea") {
    return (
      <textarea
        {...commonProps}
        value={value || ""}
        onChange={handleChange}
        placeholder={field.label}
        rows={3}
      />
    );
  }

  return (
    <input
      type={field.type || "text"}
      {...commonProps}
      value={value || ""}
      onChange={handleChange}
      placeholder={field.label}
    />
  );
}

export default function CrudForm({ entity, setEntity, fields, onSubmit, onCancel }) {
  const handleChange = (name, value) => {
    setEntity((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <form onSubmit={onSubmit} style={{ minWidth: "300px" }}>
      {fields.map((f) => (
        <div key={f.name} style={{ marginBottom: 12 }}>
          <label htmlFor={f.name} style={{ display: "block", fontWeight: 600, marginBottom: 4 }}>
            {f.label}
          </label>
          <FormInput field={f} value={entity[f.name]} onChange={handleChange} />
        </div>
      ))}

      <div style={{ display: "flex", justifyContent: "flex-end", gap: 12, marginTop: 20 }}>
        <button type="button" className="btn" onClick={onCancel} style={{ border: "1px solid #ccc", background: "#f8f9fa" }}>
          Cancelar
        </button>
        <button type="submit" className="btn btn-primary">
          Salvar
        </button>
      </div>
    </form>
  );
}