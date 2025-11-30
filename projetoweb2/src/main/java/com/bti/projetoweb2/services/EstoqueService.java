package com.bti.projetoweb2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Estoque;
import com.bti.projetoweb2.repositories.EstoqueRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Page<Estoque> listarTodos(Pageable pageable) {
        Page<Estoque> estoque = estoqueRepository.findAll(pageable);

        if (estoque.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma estoque encontrado");
        }

        return estoque;
    }

    public Estoque buscarPorId(Long id) {
        return estoqueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estoque nao encontrado"));
    }

    public Estoque salvar(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizar(Long id, Estoque estoqueAtualizado) {
        Estoque estoqueExistente = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque nao encontrado"));

        estoqueExistente.setCodigoProduto(estoqueAtualizado.getCodigoProduto());
        estoqueExistente.setNomeProduto(estoqueAtualizado.getNomeProduto());
        estoqueExistente.setQuantidade(estoqueAtualizado.getQuantidade());
        estoqueExistente.setUnidadeMedida(estoqueAtualizado.getUnidadeMedida());
        estoqueExistente.setDataValidade(estoqueAtualizado.getDataValidade());

        return estoqueRepository.save(estoqueExistente);
    }

    public void excluir(Long id) {
        estoqueRepository.deleteById(id);
    }
}
