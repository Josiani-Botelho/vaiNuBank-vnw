package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.repositorio.RepositorioContas;

import java.util.Objects;

public class BuscaConta {

    private final RepositorioContas repositorioContas;

    public BuscaConta(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public Conta executar(Long numeroConta) {
        return repositorioContas.procurarPeloNumero(numeroConta);
    }

}
