package com.sistemabancario.opcoes;

import com.sistemabancario.entidades.Banco;
import com.sistemabancario.servicos.BancoServicos;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;

public class ListarBancos extends Opcao {
    private BancoServicos bancoServicos;

    public ListarBancos(BancoServicos bancoServicos) {
        super("Listar Bancos");

        this.bancoServicos = bancoServicos;
    }

    public void executar() {
        ArrayList<Banco> bancos = this.bancoServicos.pegarTudo();
        System.out.printf(":: Listando %d bancos ::\n", bancos.size());
        for (Banco banco: bancos) {
            banco.InfoBanco();
        }
    }
}
