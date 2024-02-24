package com.josianilima.vainubank.repositorio.impl;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.repositorio.RepositorioContas;

import java.util.ArrayList;
import java.util.List;

public class RepositorioContasImpl implements RepositorioContas {

    private List<Conta> contas = new ArrayList<>();

    @Override
    public void salvar(Conta conta) {
        contas.add(conta);
    }

    @Override
    public Conta procurarPeloNumero(Long numeroDaConta) {
        return contas.stream()
                .filter(conta -> conta.getNumeroConta().equals(numeroDaConta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    @Override
    public List<Conta> buscarTodos() {
        return contas;
    }
}
