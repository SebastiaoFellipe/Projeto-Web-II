package com.bti.projetoweb2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Reabilitacao;
import com.bti.projetoweb2.repositories.FuncionarioRepository;
import com.bti.projetoweb2.repositories.ReabilitacaoRepository;

@Service
public class ReabilitacaoService {
    @Autowired
    private final ReabilitacaoRepository reabilitacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public ReabilitacaoService(ReabilitacaoRepository reabilitacaoRepository, FuncionarioRepository funcionarioRepository) {
        this.reabilitacaoRepository = reabilitacaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<Reabilitacao> listarTodos(Pageable pageable) {
        Page<Reabilitacao> reabilitacoes = reabilitacaoRepository.findAll(pageable);

        return reabilitacoes;
    }

    public Reabilitacao buscarPorId(Long id) {
        return reabilitacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Reabilitacao não encontrada com id: " + id));
    }

    public Reabilitacao salvar(Reabilitacao reabilitacao) {
        Long funcionarioId = reabilitacao.getFuncionario() != null ? reabilitacao.getFuncionario().getId() : null;

        if (funcionarioId == null || !funcionarioRepository.existsById(funcionarioId)) {
            throw new RuntimeException("Funcionário não encontrado com ID: " + funcionarioId);
        }

        return reabilitacaoRepository.save(reabilitacao);
    }

    public Reabilitacao atualizar(Long id, Reabilitacao reabilitacaoAtualizado) {
        Reabilitacao reabilitacaoExistente = buscarPorId(id);

        Long funcionarioId = reabilitacaoAtualizado.getFuncionario() != null ? reabilitacaoAtualizado.getFuncionario().getId() : null;
        if (funcionarioId == null || !funcionarioRepository.existsById(funcionarioId)) {
            throw new RuntimeException("Funcionário não encontrado com ID: " + funcionarioId);
        }

        reabilitacaoExistente.setMotivo(reabilitacaoAtualizado.getMotivo());
        reabilitacaoExistente.setTratamento(reabilitacaoAtualizado.getTratamento());
        reabilitacaoExistente.setAnimal(reabilitacaoAtualizado.getAnimal());
        reabilitacaoExistente.setDataEntrada(reabilitacaoAtualizado.getDataEntrada());
        reabilitacaoExistente.setDataSaida(reabilitacaoAtualizado.getDataSaida());
        reabilitacaoExistente.setStatus(reabilitacaoAtualizado.getStatus());
        reabilitacaoExistente.setObservacoes(reabilitacaoAtualizado.getObservacoes());
        reabilitacaoExistente.setFuncionario(reabilitacaoAtualizado.getFuncionario());

        return reabilitacaoRepository.save(reabilitacaoExistente);
    }

    public void deletar(Long id) {
        reabilitacaoRepository.deleteById(id);
    }
}
