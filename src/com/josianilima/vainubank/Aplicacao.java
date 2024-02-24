package com.josianilima.vainubank;

import com.josianilima.vainubank.dominio.Conta;
import com.josianilima.vainubank.dominio.Pessoa;
import com.josianilima.vainubank.repositorio.RepositorioContas;
import com.josianilima.vainubank.repositorio.impl.RepositorioContasImpl;
import com.josianilima.vainubank.servicos.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {

    private final AdicionarDependente adicionarDependente;
    private final BuscaConta buscaConta;
    private final CadastroContaPoupanca cadastroContaPoupanca;
    private final CadastroContaCorrente cadastroContaCorrente;
    private final Depositar depositar;
    private final ExcluirDependente excluirDependente;
    private final ListarContas listarContas;
    private final Saque saque;
    private final Transferir transferir;
    private Scanner scanner = new Scanner(System.in);

    public Aplicacao() {
        RepositorioContas repositorio = new RepositorioContasImpl();
        this.adicionarDependente = new AdicionarDependente(repositorio);
        this.buscaConta = new BuscaConta(repositorio);
        this.cadastroContaPoupanca = new CadastroContaPoupanca(repositorio);
        this.cadastroContaCorrente = new CadastroContaCorrente(repositorio);
        this.depositar = new Depositar(repositorio);
        this.excluirDependente = new ExcluirDependente(repositorio);
        this.listarContas = new ListarContas(repositorio);
        this.saque = new Saque(repositorio);
        this.transferir = new Transferir(repositorio);
    }


    public void start() {
        while (true){
            mostrarMenu();
            String opcao = scanner.nextLine();
            switch (opcao){
                case "1" -> criarConta();
                case "2" -> entrarNaConta();
                case "3" -> depositar();
                case "4" -> listarContas();
                default -> System.out.println("opção inválida");
            }
        }

    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("Seja Bem Vindo!");
        System.out.println("1. criar conta");
        System.out.println("2. entrar na conta");
        System.out.println("3. depositar");
        System.out.println("4. listar contas");
        System.out.print("Digite a opção: ");
    }

    private void listarContas() {
        System.out.println(listarContas.executar());
    }

    private void criarConta() {
        System.out.println("Digites as informações solicitadas:");
        System.out.print("Nome completo do titular: ");
        String nomeTitular = scanner.nextLine();
        System.out.print("Cpf do titular: ");
        String cpf = scanner.nextLine();
        Integer opcaoConta = null;
        while (opcaoConta == null) {

            try {
                System.out.print("Digite 1 para conta corrente ou 2 para poupança: ");
                String tipoConta = scanner.nextLine();
                opcaoConta = Integer.parseInt(tipoConta);
                if (opcaoConta > 2 || opcaoConta < 1) {
                    throw new RuntimeException();
                }
            } catch (Exception ex) {
                System.out.println("Entrada inválida");
                opcaoConta = null;
            }

        }

        System.out.println("Obrigado pelas informações, criando conta...");
        Pessoa titular = new Pessoa(nomeTitular, cpf);
        if (opcaoConta.equals(1)) {
            cadastroContaCorrente.execute(titular);
        } else {
            cadastroContaPoupanca.execute(titular);
        }
        System.out.println("Cadastro realizado com sucesso!");
    }

    private void entrarNaConta() {

        Conta conta = null;
        while (true) {
            try {
                System.out.print("Digite o número da conta: ");
                String numeroContaEntrada = scanner.nextLine();
                Long numeroConta = Long.parseLong(numeroContaEntrada);
                conta = buscaConta.executar(numeroConta);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida");
                if (opcaoRetorno()) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                if (opcaoRetorno()) {
                    break;
                }
            }
        }

        if (conta != null) {
            areaConta(conta);
        }

    }

    private void areaConta(Conta conta){

        boolean sair = false;
        while(!sair){
            System.out.println(conta);
            System.out.println("Opções da conta: ");
            System.out.println("1. Depósito");
            System.out.println("2. Saque");
            System.out.println("3. Adicionar Dependentes");
            System.out.println("4. Remover Dependentes");
            System.out.println("5. Editar Dependentes");
            System.out.println("6. Transferência");
            System.out.println("7. Sair");
            System.out.print("Digite a opção desejada ou qualquer número para retornar: ");
            String entrada = scanner.nextLine();

            switch (entrada){
                case  "1" -> depositar(conta);
                case  "2" -> sacar(conta);
                case  "3" -> adicionarDependentes(conta);
                case  "4" -> removerDependente(conta);
                case  "5" -> editarDependente(conta);
                case  "6" -> transferirEntreContas(conta);
                case  "7" -> sair = true;
                default -> System.out.println("Entrada invalida");
            }

        }
    }

    private void editarDependente(Conta conta) {
        try {
            System.out.println("Digite o CPF do dependente a editar");
            String cpf = scanner.nextLine();
            System.out.println("Digite o nome completo do dependente");
            String nome = scanner.nextLine();
            Pessoa dependente = new Pessoa(nome, cpf);
            adicionarDependente.executar(conta.getNumeroConta(),dependente);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void removerDependente(Conta conta) {
        try {
            System.out.println("Digite o CPF do dependente a remover");
            String cpf = scanner.nextLine();
            excluirDependente.executar(conta.getNumeroConta(),cpf);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void transferirEntreContas(Conta conta) {

        try {
            System.out.println("Digite o valor que deseja transferir");
            Double valor = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Digite o número da conta de destino");
            Long contaDestino = scanner.nextLong();
            scanner.nextLine();
            transferir.executar(conta.getNumeroConta(),contaDestino,valor);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void adicionarDependentes(Conta conta) {
        try {
            System.out.println("Digite o nome completo do dependente");
            String nome = scanner.nextLine();
            System.out.println("Digite o CPF do dependente");
            String cpf = scanner.nextLine();
            Pessoa dependente = new Pessoa(nome, cpf);
            adicionarDependente.executar(conta.getNumeroConta(),dependente);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void sacar(Conta conta) {
        try {
            System.out.println("Digite o valor para sacar");
            Double input = scanner.nextDouble();
            scanner.nextLine();
            saque.executar(conta.getNumeroConta(), input);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        } catch (Exception ex){
            System.out.println("O valor informado não é válido");
        }
    }

    private void depositar() {
        try {

            System.out.println("Digite o numero da conta para depositar");
            Long numeroConta = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Digite o valor para depositar");
            Double valor = scanner.nextDouble();
            scanner.nextLine();
            depositar.executar(numeroConta, valor);
        } catch (Exception ex){
            System.out.println("O valor informado não é válido");
        }
    }

    private void depositar(Conta conta) {
        try {
            System.out.println("Digite o valor para depositar");
            Double input = scanner.nextDouble();
            scanner.nextLine();
            depositar.executar(conta.getNumeroConta(), input);
        } catch (Exception ex){
            System.out.println("O valor informado não é válido");
        }
    }

    private boolean opcaoRetorno() {
        System.out.print("Deseja retornar ao menu anterior?: S/N");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("s");
    }

}
