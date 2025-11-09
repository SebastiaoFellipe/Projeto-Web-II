package com.bti.projetoweb2.animais.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bti.projetoweb2.animais.entities.Reabilitacao;
import com.bti.projetoweb2.animais.repositories.ReabilitacaoRepository;

@Service
public class ReabilitacaoService {
    private final ReabilitacaoRepository reabilitacaoRepository;

    public ReabilitacaoService(ReabilitacaoRepository reabilitacaoRepository) {
        this.reabilitacaoRepository = reabilitacaoRepository;
    }

    public List<Reabilitacao> listarTodos() {
        return reabilitacaoRepository.findAll();
    }

    public Reabilitacao buscarPorId(Long id) {
        return reabilitacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Reabilitacao não encontrada com id: " + id));
    }

    public Reabilitacao salvar(Reabilitacao reabilitacao) {
        return reabilitacaoRepository.save(reabilitacao);
    }

    public Reabilitacao atualizar(Long id, Reabilitacao reabilitacaoAtualizado) {
        Reabilitacao reabilitacaoExistente = buscarPorId(id);
        reabilitacaoExistente.setMotivo(reabilitacaoAtualizado.getMotivo());
        reabilitacaoExistente.setTratamento(reabilitacaoAtualizado.getTratamento());
        reabilitacaoExistente.setAnimal(reabilitacaoAtualizado.getAnimal());
        reabilitacaoExistente.setDataEntrada(reabilitacaoAtualizado.getDataEntrada());
        reabilitacaoExistente.setDataSaida(reabilitacaoAtualizado.getDataSaida());
        reabilitacaoExistente.setStatus(reabilitacaoAtualizado.getStatus());
        reabilitacaoExistente.setObservacoes(reabilitacaoAtualizado.getObservacoes());
        return reabilitacaoRepository.save(reabilitacaoExistente);
    }

    public void deletar(Long id) {
        reabilitacaoRepository.deleteById(id);
    }
}
