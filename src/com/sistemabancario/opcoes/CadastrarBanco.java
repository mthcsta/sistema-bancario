package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.Banco;
import com.sistemabancario.servicos.BancoServicos;

import java.util.Scanner;

public class CadastrarBanco extends Opcao {
    private BancoServicos bancoServicos;
    private Scanner input = new Scanner(System.in);

    public CadastrarBanco(BancoServicos bancoServicos) {
        super("Cadastrar Banco");

        this.bancoServicos = bancoServicos;
    }

    public void executar() {
        String nome;
        String cnpj;
        Banco banco;

        System.out.println("Preencha abaixo as informações do banco");

        System.out.print("Nome: ");
        nome = input.nextLine();

        System.out.print("CNPJ: ");
        cnpj = input.nextLine();

        banco = this.bancoServicos.inserirNovo(nome, cnpj);
        System.out.printf("Banco '%s' cadastrado com sucesso.\n", banco.getNumeroENome());
    }

}
