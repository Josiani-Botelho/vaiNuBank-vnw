package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.Pessoa;
import com.josianilima.vainubank.repositorio.RepositorioContas;

import java.util.Objects;

public class ExcluirDependente {

    private final RepositorioContas repositorioContas;

    public ExcluirDependente(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public void executar(Long numeroConta, String cpf) {

        Conta conta = repositorioContas.procurarPeloNumero(numeroConta);

        Pessoa dependende = conta.getDependentes().stream()
                .filter(pessoa -> pessoa.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dependente não encontrado"));

        conta.getDependentes().remove(dependende);

    }

}
