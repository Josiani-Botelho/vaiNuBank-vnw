package com.josianilima.vainubank.repositorio;

import com.josianilima.vainubank.dominio.Conta;

import java.util.List;


public interface RepositorioContas {

    void salvar(Conta conta);
    Conta procurarPeloNumero(Long numeroDaConta);
    List<Conta> buscarTodos();

}
