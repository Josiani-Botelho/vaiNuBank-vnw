package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.repositorio.RepositorioContas;

import java.util.Objects;

public class Depositar {

    private final RepositorioContas repositorioContas;

    public Depositar(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }


    public void executar(Long numeroConta, Double valor){

        Conta conta = repositorioContas.procurarPeloNumero(numeroConta);
        conta.depositar(valor);

    }
}
