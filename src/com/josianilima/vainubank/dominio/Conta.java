package com.josianilima.vainubank.dominio;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Conta {

    public static final String AGENCIA = "00538-9";
    private static Long id = 0L;
    private final Long numeroConta;
    private Double saldo = 0.0;

    private Pessoa titular;

    private Set<Pessoa> dependentes = new HashSet<>();

    public Conta(Pessoa titular) {
        this.numeroConta = ++id;
        this.titular = titular;
    }

    public abstract Double sacar(double saque);

    public void depositar(Double valor){
        saldo += valor;
    }

    public void tranferir(Double valor, Conta destinatario){
        this.sacar(valor);
        destinatario.depositar(valor);
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public Set<Pessoa> getDependentes() {
        return dependentes;
    }

    public String getNomeTitular() {
        return titular.getNome();
    }

    public String getCpfTitular() {
        return titular.getCpf();
    }

    public Pessoa getTitular() {
        return titular;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numeroConta=" + numeroConta +
                ", saldo=" + saldo +
                ", titular=" + titular.toString() +
                ", dependentes=" + dependentes.stream().map(Pessoa::toString).collect(Collectors.joining("|")) +
                '}';
    }

}
