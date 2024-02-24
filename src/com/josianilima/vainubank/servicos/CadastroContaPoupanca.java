package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.ContaPoupanca;
import com.josianilima.vainubank.dominio.Pessoa;
import com.josianilima.vainubank.repositorio.RepositorioContas;

public class CadastroContaPoupanca {

    private final Double TAXA_SAQUE = 0.02;
    private final RepositorioContas repositorioContas;

    public CadastroContaPoupanca(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public void execute(Pessoa pessoa) {
        Conta contaPoupanca = new ContaPoupanca(pessoa, TAXA_SAQUE);
        repositorioContas.salvar(contaPoupanca);
    }


}
