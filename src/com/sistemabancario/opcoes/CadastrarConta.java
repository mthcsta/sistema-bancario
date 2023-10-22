package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.*;
import com.sistemabancario.enums.ContaBancariaTipo;
import com.sistemabancario.servicos.BancoServicos;
import com.sistemabancario.servicos.ContasServicos;
import com.sistemabancario.servicos.PessoaServicos;

import java.util.*;

public class CadastrarConta extends Opcao {
    private BancoServicos bancoServicos;
    private PessoaServicos pessoaServicos;
    private ContasServicos contasServicos;
    private Scanner input = new Scanner(System.in);

    public CadastrarConta(BancoServicos bancoServicos, PessoaServicos pessoaServicos, ContasServicos contasServicos) {
        super("Cadastrar Conta");

        this.pessoaServicos = pessoaServicos;
        this.bancoServicos = bancoServicos;
        this.contasServicos = contasServicos;
    }

    public void executar() {
        try {
            ContaBancariaTipo contaBancariaTipo = this.escolheTipoConta();
            ContaBancaria contaBancaria = contaBancariaTipo.getForm().setServicos(this.pessoaServicos, this.bancoServicos).iniciar().getInstancia();
            this.contasServicos.inserirNovo(contaBancaria);
            System.out.printf("Conta %s cadastrada.\n", contaBancariaTipo.getTitulo());
            System.out.println(contaBancaria);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ContaBancariaTipo escolheTipoConta() throws IllegalAccessException {
        int tipoConta;

        System.out.println("Selecione o tipo de conta: ");
        for (ContaBancariaTipo contaBancariaTipo : ContaBancariaTipo.values()) {
            System.out.printf("%d - %s\n", contaBancariaTipo.getIdentificador(), contaBancariaTipo.getTitulo());
        }

        tipoConta = input.nextInt();

        return ContaBancariaTipo.getContaBancariaTipo(tipoConta);
    }

}
