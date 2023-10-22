package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.Pessoa;
import com.sistemabancario.helpers.Helpers;
import com.sistemabancario.servicos.PessoaServicos;

import java.util.Scanner;

public class CadastrarPessoa extends Opcao {
    private PessoaServicos pessoaServicos;
    private Scanner input = new Scanner(System.in);

    public CadastrarPessoa(PessoaServicos pessoaServicos) {
        super("Cadastrar Pessoa");

        this.pessoaServicos = pessoaServicos;
    }

    public void executar() {
        int idade;
        String nome;
        String sobrenome;
        String cpf;
        Pessoa pessoa;

        System.out.println("Preencha abaixo as informações da pessoa");

        System.out.print("Nome: ");
        nome = input.nextLine();

        System.out.print("Sobrenome: ");
        sobrenome = input.nextLine();

        System.out.print("Idade: ");
        idade = input.nextInt();
        Helpers.limpaBuffer();

        cpf = this.tentaRegistrarCPF();

        pessoa = this.pessoaServicos.inserirNovo(nome, sobrenome, idade, cpf);
        System.out.printf("Pessoa '%s (%s)' cadastrada com sucesso.\n", pessoa.getNomeCompleto(), pessoa.getCPF());
    }

    private String tentaRegistrarCPF() {
        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = input.next();

            if (this.pessoaServicos.cpfJaCadastrado(cpf)) {
                cpf = null;
                System.out.println("CPF já cadastrado!");
            }
        } while (cpf == null);
        return cpf;
    }


}
