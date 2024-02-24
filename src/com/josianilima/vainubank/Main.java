package com.josianilima.vainubank;

import com.josianilima.vainubank.Aplicacao;
import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.ContaPoupanca;
import com.josianilima.vainubank.dominio.Pessoa;
import com.josianilima.vainubank.repositorio.impl.RepositorioContasImpl;

import java.util.List;

public class Main {

    private static Aplicacao aplicacao = new Aplicacao();


    public static void main(String[] args) {
        aplicacao.start();
    }


}