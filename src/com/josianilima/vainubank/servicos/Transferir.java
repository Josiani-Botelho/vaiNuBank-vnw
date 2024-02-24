package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.repositorio.RepositorioContas;

import java.util.Objects;

public class Transferir {

    private final RepositorioContas repositorioContas;

    public Transferir(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public void executar(Long origem, Long destinatario, Double valor) {

        if(Objects.equals(origem,destinatario)) {
            throw new RuntimeException("Destinatário de Origem devem ser diferentes");
        }

        Conta contaOrigem = repositorioContas.procurarPeloNumero(origem);
        Conta contaDestinatario = repositorioContas.procurarPeloNumero(destinatario);

        contaOrigem.sacar(valor);
        contaDestinatario.depositar(valor);

    }
}
