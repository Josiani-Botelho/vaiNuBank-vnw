package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.repositorio.RepositorioContas;

public class Saque {

    private final RepositorioContas repositorioContas;

    public Saque(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public void executar(Long numeroDaConta, Double valor){
        Conta conta = repositorioContas.procurarPeloNumero(numeroDaConta);
        conta.sacar(valor);
    }

}
