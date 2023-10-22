package com.sistemabancario.opcoes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Opcoes<T> {
    private Map<T, Opcao> opcoes = new LinkedHashMap<T, Opcao>();

    public Opcoes() {
    }

    public void inserir(T chave, Opcao opcao) {
        opcoes.put(chave, opcao);
    }

    public void exibir() {
        for (Map.Entry<T, Opcao> opcao: opcoes.entrySet()) {
            System.out.printf("%s - %s\n", opcao.getKey().toString(), opcao.getValue().getTitulo());
        }
    }

    public void executar(T chave) {
        Opcao selecionada;
        if (!opcoes.containsKey(chave)) {
            OpcaoInvalida.executar();
            return;
        }
        selecionada = opcoes.get(chave);
        selecionada.executar();
    }

}
