export default function Modal({ isOpen, onClose, title, children }) {
  if (!isOpen) {
    return null;
  }

  return (
    // Backdrop
    <div 
      className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50"
      onClick={onClose} // Fecha ao clicar no backdrop
    >
      {/* Painel do Modal */}
      <div 
        className="relative w-full max-w-lg rounded-lg bg-white p-6 shadow-xl"
        onClick={(e) => e.stopPropagation()} // Impede de fechar ao clicar no painel
      >
        {/* Cabeçalho */}
        <div className="flex items-center justify-between pb-3 border-b border-gray-200">
          <h3 className="text-xl font-semibold text-gray-900">{title}</h3>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-600 text-3xl leading-none"
          >
            &times;
          </button>
        </div>
        
        {/* Conteúdo (o formulário) */}
        <div className="mt-4">
          {children}
        </div>
      </div>
    </div>
  );
}