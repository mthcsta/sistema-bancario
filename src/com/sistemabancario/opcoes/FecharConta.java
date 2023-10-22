package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.ContaBancaria;
import com.sistemabancario.servicos.ContasServicos;

import java.util.Scanner;

public class FecharConta extends Opcao {
    private ContasServicos contasServicos;
    private Scanner input = new Scanner(System.in);

    public FecharConta(ContasServicos contasServicos) {
        super("Fechar Conta");

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
        prosseguirExecucao(contaBancaria);
    }

    public void prosseguirExecucao(ContaBancaria contaBancaria) {
        String resposta;

        contaBancaria.info();
        System.out.println("Deseja mesmo excluir essa conta? Se deseja prosseguir com a exclusão digite SIM");

        resposta = input.next();

        if (!resposta.equalsIgnoreCase("sim")) {
            System.out.println("Conta não excluída.");
            return;
        }

        this.contasServicos.removerConta(contaBancaria);
        System.out.println("Conta excluída.");
    }
}
