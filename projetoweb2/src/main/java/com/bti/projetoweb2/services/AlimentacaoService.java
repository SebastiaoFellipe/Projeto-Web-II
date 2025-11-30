package com.bti.projetoweb2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Alimentacao;
import com.bti.projetoweb2.repositories.AlimentacaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlimentacaoService {
    private final AlimentacaoRepository alimentacaoRepository;

    public AlimentacaoService(AlimentacaoRepository alimentacaoRepository) {
        this.alimentacaoRepository = alimentacaoRepository;
    }

    public Page<Alimentacao> listarTodos(Pageable pageable) {
        Page<Alimentacao> alimentacao = alimentacaoRepository.findAll(pageable);

        if (alimentacao.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma alimentacao encontrado");
        }

        return alimentacao;
    }

    public Alimentacao buscarPorId(Long id) {
        return alimentacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alimentacao com ID " + id + " não encontrado"));
    }

    public Alimentacao cadastrar(Alimentacao alimentacao) {
        return alimentacaoRepository.save(alimentacao);
    }

    public Alimentacao atualizar(Long id, Alimentacao alimentacaoAtualizada) {
        Alimentacao alimentacaoExistente = buscarPorId(id);

        alimentacaoExistente.setTipoAlimentacao(alimentacaoAtualizada.getTipoAlimentacao());
        alimentacaoExistente.setQuantidade(alimentacaoAtualizada.getQuantidade());
        alimentacaoExistente.setDataAlimentacao(alimentacaoAtualizada.getDataAlimentacao());
        alimentacaoExistente.setObservacoes(alimentacaoAtualizada.getObservacoes());
        alimentacaoExistente.setFuncionario(alimentacaoAtualizada.getFuncionario());
        alimentacaoExistente.setAnimal(alimentacaoAtualizada.getAnimal());
        alimentacaoExistente.setEstoque(alimentacaoAtualizada.getEstoque());

        return alimentacaoRepository.save(alimentacaoExistente);
    }

    public void deletar(Long id) {
        Alimentacao alimentacaoExistente = buscarPorId(id);
        alimentacaoRepository.delete(alimentacaoExistente);
    }
}
