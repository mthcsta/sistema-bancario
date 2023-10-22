package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.ContaBancaria;
import com.sistemabancario.servicos.ContasServicos;
import java.util.ArrayList;

public class PassarMes extends Opcao {
    private ContasServicos contasServicos;

    public PassarMes(ContasServicos contasServicos) {
        super("Passar Mês");

        this.contasServicos = contasServicos;
    }

    public void executar() {
        ArrayList<ContaBancaria> contas = this.contasServicos.pegarTudo();
        System.out.printf("Atualizando %d contas.\n", contas.size());
        for (ContaBancaria conta: contas) {
            conta.novoMes();
        }
    }
}
