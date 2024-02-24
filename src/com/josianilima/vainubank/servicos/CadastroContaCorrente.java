package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.ContaCorrente;
import com.josianilima.vainubank.dominio.Pessoa;
import com.josianilima.vainubank.repositorio.RepositorioContas;

public class CadastroContaCorrente {

    private final Double LIMITE_CONTA_CORRENTE = 1000.0;
    private final RepositorioContas repositorioContas;

    public CadastroContaCorrente(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public void execute(Pessoa pessoa) {
        Conta contaCorrente = new ContaCorrente(pessoa, LIMITE_CONTA_CORRENTE);
        repositorioContas.salvar(contaCorrente);
    }


}
