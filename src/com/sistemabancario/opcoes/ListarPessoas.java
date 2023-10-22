package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.Pessoa;
import com.sistemabancario.servicos.PessoaServicos;

public class ListarPessoas extends Opcao {
    private PessoaServicos pessoaServicos;

    public ListarPessoas(PessoaServicos pessoaServicos) {
        super("Listar Pessoas");

        this.pessoaServicos = pessoaServicos;
    }

    public void executar() {
        for (Pessoa pessoa: this.pessoaServicos.pegarTudo()) {
            System.out.println(pessoa);
        }
    }
}
