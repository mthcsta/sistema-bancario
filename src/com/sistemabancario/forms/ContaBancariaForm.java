package com.sistemabancario.forms;

import com.sistemabancario.construtores.ContaBancariaConstrutor;
import com.sistemabancario.entidades.Banco;
import com.sistemabancario.entidades.ContaBancaria;
import com.sistemabancario.entidades.Pessoa;
import com.sistemabancario.servicos.BancoServicos;
import com.sistemabancario.servicos.PessoaServicos;

import java.util.Scanner;

public abstract class ContaBancariaForm<T extends ContaBancariaConstrutor> {

    private Scanner input;
    private T contaBancariaBuilder;
    private PessoaServicos pessoaServicos;
    private BancoServicos bancoServicos;

    public ContaBancariaForm(T builder) {
        this.contaBancariaBuilder = builder;
    }

    public ContaBancariaForm<T> iniciar() {
        input = new Scanner(System.in);
        Pessoa titular;
        Banco banco;
        int numeroConta;
        String senha;

        System.out.println("Preencha abaixo as informações da conta bancaria");

        titular = this.BuscaPessoaPorCPF();
        System.out.printf("Nome da Pessoa: %s\n", titular.getNomeCompleto());

        banco = this.BuscaBancoPorNumero();
        System.out.printf("Banco %s\n", banco.getNumeroENome());

        System.out.print("Senha da conta: ");
        senha = input.next();

        numeroConta = banco.novoNumeroConta();

        getBuilder().titular(titular)
                .banco(banco)
                .numeroConta(numeroConta)
                .senha(senha);

        alternativo();

        return this;
    }

    public ContaBancariaForm<T> setServicos(PessoaServicos pessoaServicos, BancoServicos bancoServicos) {
        this.pessoaServicos = pessoaServicos;
        this.bancoServicos = bancoServicos;
        return this;
    }

    private Pessoa BuscaPessoaPorCPF() {
        Pessoa pessoa;
        do {
            System.out.print("CPF do titular: ");
            String pessoaCPF = input.nextLine();
            pessoa = this.pessoaServicos.buscarPorCPF(pessoaCPF);
            if (pessoa == null) {
                System.out.println("CPF não encontrado. Busque novamente.");
            }
        } while (pessoa == null);
        return pessoa;
    }

    private Banco BuscaBancoPorNumero() {
        Banco banco;
        do {
            System.out.print("Número do banco: ");
            int numeroBanco = input.nextInt();
            banco = this.bancoServicos.buscarPorNumero(numeroBanco);
            if (banco == null) {
                System.out.println("Banco não encontrado. Busque novamente.");
            }
        } while (banco == null);
        return banco;
    }

    public Scanner getInput() {
        return input;
    }

    public abstract void alternativo();
    public T getBuilder() {
        return contaBancariaBuilder;
    };
    public abstract ContaBancaria getInstancia();
}
