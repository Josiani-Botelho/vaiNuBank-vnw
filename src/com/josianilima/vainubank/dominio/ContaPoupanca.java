package com.josianilima.vainubank.dominio;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ContaPoupanca extends Conta{

    private Double taxaSaque;

    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    public ContaPoupanca(Pessoa titular, Double taxaSaque) {
        super(titular);
        this.taxaSaque = taxaSaque;
    }

    @Override public Double sacar(double valor){
        final double valorComTaxa = valor + valor * taxaSaque;
        if (this.getSaldo() >= valorComTaxa) {
            this.sacar(valorComTaxa);
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
        return this.getSaldo();
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "taxaSaque=" + taxaSaque +
                ", dataDeCriacao=" + dataDeCriacao +
                ", numeroConta=" + getNumeroConta() +
                ", saldo=" + getSaldo() +
                ", titular=" + getTitular().toString() +
                ", dependentes=" + getDependentes().stream().map(Pessoa::toString).collect(Collectors.joining("|")) +
                '}';
    }
}
