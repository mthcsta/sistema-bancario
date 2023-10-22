package com.sistemabancario;

import com.sistemabancario.entidades.*;
import com.sistemabancario.helpers.Helpers;
import com.sistemabancario.opcoes.*;
import com.sistemabancario.servicos.BancoServicos;
import com.sistemabancario.servicos.ContasServicos;
import com.sistemabancario.servicos.PessoaServicos;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static AtomicBoolean emExecucao = new AtomicBoolean(true);
    public static Opcoes<Integer> opcoes = new Opcoes<Integer>();
    public static ArrayList<Banco> bancos = new ArrayList<Banco>();
    public static ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

    public static void main(String[] args) {
        RegistrarOpcoes();
        Seed();
        IniciarExecucao();
    }

    public static void PopulaPessoas() throws URISyntaxException {
        Path caminhoDoArquivo = Path.of(Main.class.getResource(Path.of("dados", "pessoas.csv").toString()).toURI()).toAbsolutePath();

        Helpers.percorreCSV(
            caminhoDoArquivo,
            (colunas) -> pessoas.add(new Pessoa(
                    colunas.get(0),
                    colunas.get(1),
                    Integer.parseInt(colunas.get(2)),
                    colunas.get(3)
            ))
        );
    }

    public static void PopulaBancos() throws URISyntaxException {
        Path caminhoDoArquivo = Path.of(Main.class.getResource(Path.of("dados", "bancos.csv").toString()).toURI()).toAbsolutePath();

        Helpers.percorreCSV(
            caminhoDoArquivo,
            (colunas) -> bancos.add(new Banco(
                    colunas.get(0),
                    colunas.get(1),
                    Integer.parseInt(colunas.get(2))
            ))
        );
    }

    public static void PopulaContas() throws URISyntaxException {
        BancoServicos bancoServicos = new BancoServicos(bancos);
        PessoaServicos pessoaServicos = new PessoaServicos(pessoas);
        ContasServicos contasServicos = new ContasServicos(bancos, pessoas);
        Path caminhoDoArquivo = Path.of(Main.class.getResource(Path.of("dados", "contas.csv").toString()).toURI()).toAbsolutePath();

        Helpers.percorreCSV(
                caminhoDoArquivo,
                (colunas) -> {
                    Pessoa pessoa = pessoaServicos.buscarPorCPF(colunas.get(1));
                    Banco banco = bancoServicos.buscarPorNumero(Integer.parseInt(colunas.get(2)));
                    int numero = Integer.parseInt(colunas.get(3));
                    double saldo = Double.parseDouble(colunas.get(4));
                    String senha = colunas.get(5);

                    if (pessoa == null || banco == null) {
                        return false;
                    }

                    switch (colunas.get(0).toLowerCase()) {
                        case "poupança" -> {
                            double rendimento = Double.parseDouble(colunas.get(7));
                            contasServicos.inserirNovo(new ContaPoupanca(pessoa, banco, numero, senha, saldo, rendimento));
                        }
                        case "corrente" -> {
                            double taxaMensal = Double.parseDouble(colunas.get(6));
                            contasServicos.inserirNovo(new ContaCorrente(pessoa, banco, numero, senha, saldo, taxaMensal));
                        }
                    }
                    return true;
                }
        );
    }

    public static void Seed() {
        try {
            PopulaPessoas();
            PopulaBancos();
            PopulaContas();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void relacionaPessoaComBanco(ContaBancaria contaBancaria) {
        Pessoa pessoa = contaBancaria.getTitular();
        Banco banco = contaBancaria.getBanco();
        pessoa.novaContaBancaria(contaBancaria);
        banco.CriarConta(contaBancaria);
    }

    public static void RegistrarOpcoes() {
        BancoServicos bancoServicos = new BancoServicos(bancos);
        PessoaServicos pessoaServicos = new PessoaServicos(pessoas);
        ContasServicos contasServicos = new ContasServicos(bancos, pessoas);

        opcoes.inserir(1, new CadastrarBanco(bancoServicos));
        opcoes.inserir(2, new ListarBancos(bancoServicos));
        opcoes.inserir(3, new CadastrarPessoa(pessoaServicos));
        opcoes.inserir(4, new ListarPessoas(pessoaServicos));
        opcoes.inserir(5, new CadastrarConta(bancoServicos, pessoaServicos, contasServicos));
        opcoes.inserir(6, new ListarContas(contasServicos));
        opcoes.inserir(7, new FecharConta(contasServicos));
        opcoes.inserir(8, new AcessarConta(contasServicos));
        opcoes.inserir(9, new PassarMes(contasServicos));
        opcoes.inserir(0, new EncerrarAtividades(emExecucao));
    }

    public static void IniciarExecucao() {
        int opcao;
        Scanner input = new Scanner(System.in);

        while (emExecucao.get()) {
            opcoes.exibir();
            System.out.print("Escolha uma das opções acima: ");
            opcao = input.nextInt();
            Helpers.limpaBuffer();
            opcoes.executar(opcao);
        }
    }

}
