const FormInput = ({ field, value, onChange }) => {
  const handleChange = (e) => {
    onChange(field.name, e.target.value);
  };

  if (field.type === 'select') {
    return (
      <select 
        name={field.name} 
        value={value} 
        onChange={handleChange}
        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
      >
        <option value="">Selecione...</option>
        {field.options.map(opt => (
          <option key={opt.value} value={opt.value}>{opt.label}</option>
        ))}
      </select>
    );
  }

  return (
    <input
      type={field.type || 'text'}
      name={field.name}
      value={value}
      onChange={handleChange}
      placeholder={field.label}
      className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
    />
  );
};

// Formulário principal
export default function CrudForm({ entity, setEntity, fields, onSubmit, onCancel }) {
  
  const handleChange = (name, value) => {
    setEntity(prev => ({ ...prev, [name]: value }));
  };

  return (
    <form onSubmit={onSubmit} className="space-y-4">
      {fields.map(field => (
        <div key={field.name}>
          <label className="block text-sm font-medium text-gray-700">
            {field.label}
          </label>
          <FormInput
            field={field}
            value={entity[field.name] || ''}
            onChange={handleChange}
          />
        </div>
      ))}

      {/* Botões */}
      <div className="flex justify-end space-x-3 pt-4">
        <button
          type="button"
          onClick={onCancel}
          className="px-4 py-2 bg-gray-300 text-gray-800 rounded-md hover:bg-gray-400"
        >
          Cancelar
        </button>
        <button
          type="submit"
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          Salvar
        </button>
      </div>
    </form>
  );
}