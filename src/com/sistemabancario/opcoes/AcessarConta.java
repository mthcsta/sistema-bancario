package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.ContaBancaria;
import com.sistemabancario.servicos.ContasServicos;

import java.util.Scanner;

public class AcessarConta extends Opcao {
    private ContasServicos contasServicos;
    private Opcoes<Integer> opcoes = new Opcoes<Integer>();
    private Scanner input = new Scanner(System.in);

    public AcessarConta(ContasServicos contasServicos) {
        super("Acessar Conta");

        this.contasServicos = contasServicos;

    }

    public ContaBancaria acessarContaBancaria() {
        int numeroBanco;
        int numeroConta;
        ContaBancaria contaBancaria;

        do {
            System.out.print("Digite o Número do Banco e o Número da Conta (separados por ESPAÇO): ");
            numeroBanco = input.nextInt();
            numeroConta = input.nextInt();
            contaBancaria = this.contasServicos.buscarPorNumeroBancoENumeroConta(numeroBanco, numeroConta);
            if (contaBancaria == null) {
                System.out.println("Conta Bancaria não encontrada.");
            }
        } while (contaBancaria == null);

        return contaBancaria;
    }

    public void executar() {
        ContaBancaria contaBancaria = this.acessarContaBancaria();

        System.out.println("Dados da conta:");
        System.out.println(contaBancaria);
        System.out.println("------------------------------------------------");

        prosseguirOpcao(contaBancaria);
    }

    private void prosseguirOpcao(ContaBancaria contaBancaria) {
        int opcao;

        System.out.println("Escolha uma ação para realizar na conta:");
        System.out.println("1) Saque");
        System.out.println("2) Depósito");

        do {
            opcao = input.nextInt();
            switch (opcao) {
                case 1 -> contaBancaria.saque();
                case 2 -> contaBancaria.deposito();
                default -> {
                    opcao = 0;
                    System.out.println("Opção inválida.");
                }
            }
        } while (opcao == 0);
    }

}
