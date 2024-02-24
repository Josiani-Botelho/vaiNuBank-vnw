package com.josianilima.vainubank.servicos;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.ContaCorrente;
import com.josianilima.vainubank.repositorio.RepositorioContas;

import java.util.List;

public class ListarContas {

    private final String CONTA_LINHA_TEMPLATE = "%-4s . Numero: %-5s, Nome Titular: %-15s, Cpf: %-11s, Saldo: %.2f, Tipo: %s";

    private final RepositorioContas repositorioContas;

    public ListarContas(RepositorioContas repositorioContas) {
        this.repositorioContas = repositorioContas;
    }

    public String executar() {
        List<Conta> contas = repositorioContas.buscarTodos();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < contas.size(); i++) {
            stringBuilder.append(preencherLinha(i, contas.get(i))).append("\n");
        }

        return stringBuilder.toString();
    }

    private String preencherLinha(int indice, Conta conta) {
        return CONTA_LINHA_TEMPLATE.formatted(
                (indice + 1),
                conta.getNumeroConta(),
                conta.getNomeTitular(),
                conta.getCpfTitular(),
                conta.getSaldo(),
                conta instanceof ContaCorrente ? "Conta Corrente" : "Conta Poupança");
    }


}
