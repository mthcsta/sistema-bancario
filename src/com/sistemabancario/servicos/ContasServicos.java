package com.sistemabancario.servicos;

import com.sistemabancario.entidades.Banco;
import com.sistemabancario.entidades.ContaBancaria;
import com.sistemabancario.entidades.Pessoa;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ContasServicos {
    private BancoServicos bancoServicos;
    private PessoaServicos pessoaServicos;

    public ContasServicos(ArrayList<Banco> bancos, ArrayList<Pessoa> pessoas) {
        this.bancoServicos = new BancoServicos(bancos);
        this.pessoaServicos = new PessoaServicos(pessoas);
    }

    public ArrayList<ContaBancaria> pegarTudo() {
        return (ArrayList<ContaBancaria>) this.bancoServicos.pegarTudo().stream()
                .flatMap((banco) -> banco.getContasBancarias().stream())
                .collect(Collectors.toList());
    }

    public void inserirNovo(ContaBancaria contaBancaria) {
        Pessoa pessoa = contaBancaria.getTitular();
        Banco banco = contaBancaria.getBanco();

        banco.CriarConta(contaBancaria);
        pessoa.novaContaBancaria(contaBancaria);
    }

    public void removerConta(ContaBancaria contaBancaria) {
        Pessoa pessoa = contaBancaria.getTitular();
        Banco banco = contaBancaria.getBanco();

        banco.FecharConta(contaBancaria);
        pessoa.removerContaBancaria(contaBancaria);
    }

    public ContaBancaria buscarPorNumeroBancoENumeroConta(int numeroBanco, int numeroConta) {
        Banco banco = this.bancoServicos.buscarPorNumero(numeroBanco);
        if (banco == null) {
            return null;
        }
        return banco.getContasBancarias().stream().filter((contaBancaria) -> contaBancaria.getNumero() == numeroConta).findAny().orElse(null);
    }

    public ArrayList<ContaBancaria> buscarPorCPF(String cpf) {
        return (ArrayList<ContaBancaria>) this.pegarTudo().stream().filter((contaBancaria) -> contaBancaria.getTitular().getCPF().equals(cpf)).collect(Collectors.toList());
    }
}
