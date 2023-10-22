package com.sistemabancario.helpers;

import com.sistemabancario.Main;
import com.sistemabancario.entidades.Pessoa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Helpers {

    public static void limpaBuffer() {
        try {
            System.in.read(new byte[System.in.available()]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void percorreCSV(Path caminhoDoArquivo, Predicate<List<String>> percorreLinha) {
        String linha;
        try {
            Scanner scanner = new Scanner(new File(caminhoDoArquivo.toString()));

            // pula cabeçalho
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                List<String> colunas = Arrays.stream(linha.split(",")).map((coluna) -> coluna.trim().replaceAll("^\"|\"$", "")).collect(Collectors.toList());
                percorreLinha.test(colunas);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
