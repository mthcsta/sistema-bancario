package com.sistemabancario.opcoes;

import java.util.concurrent.atomic.AtomicBoolean;

public class EncerrarAtividades extends Opcao {
    private AtomicBoolean emExecucao;

    public EncerrarAtividades(AtomicBoolean emExecucao) {
        super("Encerrar Atividades");

        this.emExecucao = emExecucao;
    }

    public void executar() {
        this.emExecucao.set(false);
        System.out.println("Programa encerrado.");
    }
}
