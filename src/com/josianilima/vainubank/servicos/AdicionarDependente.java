package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.Pessoa;
import com.josianilima.vainubank.repositorio.RepositorioContas;

public class AdicionarDependente {

    private final RepositorioContas repositorioContas;

    public AdicionarDependente(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public void executar(Long numeroConta, Pessoa dependente) {

        Conta conta = repositorioContas.procurarPeloNumero(numeroConta);
        conta.getDependentes().add(dependente);

    }
}
