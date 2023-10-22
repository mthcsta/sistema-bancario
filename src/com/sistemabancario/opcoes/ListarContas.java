package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.ContaBancaria;
import com.sistemabancario.helpers.Helpers;
import com.sistemabancario.servicos.ContasServicos;

import java.util.ArrayList;
import java.util.Scanner;

public class ListarContas extends Opcao {
    private ContasServicos contasServicos;
    private Scanner input = new Scanner(System.in);

    public ListarContas(ContasServicos contasServicos) {
        super("Listar Contas");

        this.contasServicos = contasServicos;
    }

    public void executar() {
        String cpf;
        ArrayList<ContaBancaria> contaBancarias;

        System.out.print("Buscar contas por CPF (se deseja listar todas contas apenas pressione ENTER): ");
        cpf = input.nextLine();
        Helpers.limpaBuffer();

        if (cpf.length() == 0) {
            contaBancarias = this.contasServicos.pegarTudo();
        } else {
            contaBancarias = this.contasServicos.buscarPorCPF(cpf);
        }

        if (contaBancarias == null) {
            System.out.println("Nenhuma conta encontrada.");
            return;
        }

        System.out.printf(":: Listando %d contas ::\n", contaBancarias.size());

        for (ContaBancaria contaBancaria: contaBancarias) {
            contaBancaria.info();
        }
    }
}
