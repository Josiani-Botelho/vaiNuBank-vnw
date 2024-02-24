package com.josianilima.vainubank.dominio;

import java.util.stream.Collectors;

public class ContaCorrente extends Conta {

    private final double limiteCreditoMaximo;

    public ContaCorrente(Pessoa titular, Double limiteCreditoMaximo) {
        super(titular);
        this.limiteCreditoMaximo = limiteCreditoMaximo;
    }

    @Override
    public Double sacar(double valor) {
        if (getSaldo() >= valor) {
            this.setSaldo(getSaldo() - valor);
        } else if (getSaldo() - valor >= -limiteCreditoMaximo) {
            setSaldo(getSaldo() - valor);
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
        return getSaldo();
    }


    @Override
    public String toString() {
        return "ContaCorrente{" +
                "limiteCreditoMaximo=" + limiteCreditoMaximo +
                ", numeroConta=" + getNumeroConta() +
                ", saldo=" + getSaldo() +
                ", titular=" + getTitular().toString() +
                ", dependentes=" + getDependentes().stream().map(Pessoa::toString).collect(Collectors.joining("|")) +
                '}';
    }
}
